package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.ProductSaleRequest;
import uz.nova.buildingcrm.model.dto.SaleItemRequest;
import uz.nova.buildingcrm.model.entity.*;
import uz.nova.buildingcrm.model.enums.CurrencyName;
import uz.nova.buildingcrm.model.enums.SaleStatus;
import uz.nova.buildingcrm.repository.*;
import uz.nova.buildingcrm.service.AuthUserService;
import uz.nova.buildingcrm.service.SaleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final SaleRepository saleRepository;
    private final CreditSaleRepository creditSaleRepository;
    private final AuthUserService authUserService;
    private final OutputRepository outputRepository;
    private final OutputProductRepository outputProductRepository;

    @Override
    public MyResponse makeSale(ProductSaleRequest request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        AuthUser cashier = authUserService.getCurrentUser();
        for (SaleItemRequest item : request.getItems()) {

            Product product = productRepository.findByName(item.getProductName())
                    .orElseThrow(() -> new RuntimeException("Product not found"));


            Stock stock = stockRepository.findByProductNameAndWarehouseId(
                    item.getProductName(),
                    request.getWarehouseId()
            ).orElseThrow(() -> new RuntimeException("Stock not found"));

            if (stock.getQuantity() < item.getAmount()) {
                throw new RuntimeException("Not enough stock for: " + product.getName());
            }

            stock.setQuantity(stock.getQuantity() - item.getAmount());
            stockRepository.save(stock);
            if (request.getSaleType().equalsIgnoreCase("PAID")) {
                savePaidSale(item, warehouse, cashier, CurrencyName.UZS);
            } else if (request.getSaleType().equalsIgnoreCase("CREDIT")) {
                saveCreditSale(item, warehouse, cashier, request, CurrencyName.UZS);
            } else {
                throw new RuntimeException("Invalid sale type");
            }
        }
        return MyResponse.SUCCESSFULLY_CREATED;
    }

    private void savePaidSale(SaleItemRequest item, Warehouse warehouse, AuthUser cashier, CurrencyName currency) {
        Product product = productRepository.findByName(item.getProductName())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        BigDecimal total = product.getRetailPrice()
                .multiply(BigDecimal.valueOf(item.getAmount()));

        Sale sale = Sale.builder()
                .warehouse(warehouse)
                .cashier(cashier)
                .totalAmount(total)
                .date(LocalDate.now())
                .status(SaleStatus.PAID)
                .build();



        Output output = new Output();
        output.setDate(LocalDateTime.now());
        output.setCurrency(currency);
        output.setWarehouse(warehouse);
        output.setCashier(cashier);

        OutputProduct op = new OutputProduct();
        op.setProductName(product.getName());
        op.setPrice(product.getRetailPrice().doubleValue());
        op.setOutput(output);
        op.setAmount(item.getAmount().doubleValue());
        op.setProduct(product);
        op.setProductPrice(product.getRetailPrice());


        outputRepository.save(output);
        outputProductRepository.save(op);
        saleRepository.save(sale);
    }

    private void saveCreditSale(
            SaleItemRequest item,
            Warehouse warehouse,
            AuthUser cashier,
            ProductSaleRequest request,
            CurrencyName currency
    ) {
        Product product = productRepository.findByName(item.getProductName())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + item.getProductName()));


        BigDecimal retailPrice = product.getRetailPrice();

        long amount = item.getAmount();


        BigDecimal total = retailPrice.multiply(BigDecimal.valueOf(amount));

        CreditSale creditSale = CreditSale.builder()
                .customerName(request.getCustomerName())
                .phone(request.getPhone())
                .totalAmount(total)
                .paidAmount(BigDecimal.ZERO)
                .purchaseDate(LocalDate.now())
                .dueDate(request.getDueDate())
                .status(SaleStatus.CREDIT)
                .build();


        Output output = new Output();
        output.setDate(LocalDateTime.now());
        output.setCurrency(currency);
        output.setWarehouse(warehouse);
        output.setCashier(cashier);


        OutputProduct op = new OutputProduct();
        op.setProductName(product.getName());
        op.setPrice(product.getRetailPrice().doubleValue());
        op.setOutput(output);
        op.setAmount(item.getAmount().doubleValue());
        op.setProduct(product);
        op.setProductPrice(product.getRetailPrice());

        outputRepository.save(output);
        outputProductRepository.save(op);
        creditSaleRepository.save(creditSale);
    }
}