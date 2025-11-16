package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nova.buildingcrm.mapper.InputProductMapper;
import uz.nova.buildingcrm.mapper.ProductMapper;
import uz.nova.buildingcrm.model.dto.InputProductCreateDto;
import uz.nova.buildingcrm.model.dto.InputProductDto;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.ProductCardDto;
import uz.nova.buildingcrm.model.entity.*;
import uz.nova.buildingcrm.repository.*;
import uz.nova.buildingcrm.service.ProductService;
import uz.nova.buildingcrm.service.StockService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final UnitRepository unitRepository;
    private final ProductMapper productMapper;
    private final InputProductMapper inputProductMapper;
    private final WarehouseRepository warehouseRepository;
    private final InputProductRepository inputProductRepository;
    private final StockService stockService;


    @Override
    public List<ProductCardDto> getProductsForWarehouse(String warehouseId) {
        List<Stock> stocks = stockRepository.findAllByWarehouseId(warehouseId);
        return Collections.singletonList(productMapper.toDto((Stock) stocks));
    }

    @Override
    @Transactional
    public MyResponse createProduct(InputProductCreateDto dto) {
        Optional<Unit> unit = unitRepository.findById(dto.getUnitId());

        Product product = null;
        Optional<Product> p = productRepository.findByName(dto.getName());
        if (p.isPresent()) {
            product = p.get();
            product.setRetailPrice(dto.getRetailPrice());
            product.setWholesalePrice(dto.getWholesalePrice());
        } else {
            product = new Product();
            product.setName(dto.getName());
            product.setSku(dto.getSku());
            product.setUnit(unit.get());
            product.setBarcode(dto.getBarcode());
            product.setRetailPrice(dto.getRetailPrice());
            product.setBrand(dto.getBrand());
            product.setWholesalePrice(dto.getWholesalePrice());
        }

        Optional<Warehouse> warehouse = warehouseRepository.findById(dto.getWarehouseId());

        Supplier sup = null;
        Optional<Supplier> supplier = supplierRepository.findByName(dto.getBrand());
        if (!supplier.isPresent()) {
            Supplier supplier1 = new Supplier();
            supplier1.setName(dto.getBrand());
            sup = supplierRepository.save(supplier1);
        } else {
            sup = supplier.get();
        }

        productRepository.save(product);
        InputProduct create = inputProductMapper.toEntityForCreate(dto, sup, warehouse.get(), product, unit.get());
        inputProductRepository.save(create);

        Optional<Stock> stock = stockService.getStockByProductNameNameAndByWarehouseName(dto.getName(), warehouse.get().getName());
        if (stock.isPresent()) {
            Stock s = stock.get();
            s.setQuantity(s.getQuantity() + dto.getQuantity());
            stockRepository.save(s);
        }else {
            Stock newStock = new Stock();
            newStock.setQuantity(dto.getQuantity());
            newStock.setProduct(product);
            newStock.setWarehouse(warehouse.get());
            newStock.setMinStock(dto.getMinStock());
            newStock.setActive(true);
            stockRepository.save(newStock);
        }
        return MyResponse.SUCCESSFULLY_CREATED;
    }




    @Override
    @Transactional
    public MyResponse addQuantityById(String inputId, Integer quantity) {

        Optional<InputProduct> p = inputProductRepository.findById(inputId);
        if (!p.isPresent()) {
            return MyResponse.INPUT_PRODUCT_NOT_FOUND;
        }
        if (quantity <= 0) {
            return MyResponse.INVALID_QUANTITY;
        }
        Integer n = p.get().getQuantity();

        p.get().setQuantity(n + quantity);
        inputProductRepository.save(p.get());
        return MyResponse.SUCCESSFULLY_UPDATED;
    }

    @Override
    @Transactional
    public MyResponse updateProduct(String productId, InputProductDto dto) {

        Optional<InputProduct> ip = inputProductRepository.findById(productId);
        if (!ip.isPresent()) {
            return MyResponse.INPUT_PRODUCT_NOT_FOUND;
        }

        ip.get().setQuantity(dto.getQuantity());
        ip.get().setSupplier(supplierRepository.findByName(dto.getBrand()).get());
        ip.get().setUnit(unitRepository.findById(dto.getUnitId()).get());
        ip.get().setWarehouse(warehouseRepository.findById(dto.getWarehouseId()).get());
        ip.get().setProduct(productRepository.findById(productId).get());
        ip.get().setUnit(unitRepository.findById(dto.getUnitId()).get());
        ip.get().setMinStock(dto.getMinStock());
        ip.get().setDate(dto.getDate());
        ip.get().setCurrency(dto.getCurrencyName());
        ip.get().setOosPercentage(dto.getOosPercentage());
        ip.get().setPrice(dto.getRetailPrice());

        inputProductRepository.save(ip.get());
        return MyResponse.SUCCESSFULLY_UPDATED;
    }

    @Override
    @Transactional
    public MyResponse deleteProduct(String productId) {
        Optional<InputProduct> p = inputProductRepository.findById(productId);
        if (!p.isPresent()) {
            return MyResponse.INPUT_PRODUCT_NOT_FOUND;
        }
        p.get().getProduct().setActive(false);
        inputProductRepository.save(p.get());
        return MyResponse.SUCCESSFULLY_DELETED;
    }


    @Override
    public List<InputProductDto> getAll() {
        return inputProductRepository.findAll().stream().filter(ip -> ip.getProduct().getActive()).map(inputProductMapper::toDto).toList();

    }
}
