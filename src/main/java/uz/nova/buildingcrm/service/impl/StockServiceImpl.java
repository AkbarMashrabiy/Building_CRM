package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;
import uz.nova.buildingcrm.mapper.StockMapper;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.StockDto;
import uz.nova.buildingcrm.model.entity.Product;
import uz.nova.buildingcrm.model.entity.Stock;
import uz.nova.buildingcrm.model.entity.Warehouse;
import uz.nova.buildingcrm.repository.ProductRepository;
import uz.nova.buildingcrm.repository.StockRepository;
import uz.nova.buildingcrm.repository.WarehouseRepository;
import uz.nova.buildingcrm.service.StockService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final StockMapper stockMapper;


    @Override
    public MyResponse updateQuantity(String id, Integer quantity) {
        return null;
    }

    @Override
    public List<StockDto> getAllByWarehouseId(String warehouseId) {
       return stockRepository.findAllByWarehouseId(warehouseId).stream().map(stockMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getLowStockItems(String warehouseId) {
        return stockRepository.findLowStockItems(warehouseId).stream().map(stockMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalPriceOfWarehouse(String warehouseId) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<Stock> allByWarehouseId = stockRepository.findAllByWarehouseId(warehouseId);

        for (Stock stock : allByWarehouseId) {
            BigDecimal retailPrice = stock.getProduct().getRetailPrice();
            Integer quantity = stock.getQuantity();

            if (retailPrice != null && quantity != null) {
                BigDecimal quantityBD = BigDecimal.valueOf(quantity);
                totalPrice = totalPrice.add(retailPrice.multiply(quantityBD));
            }
        }
        return totalPrice;
    }

    @Override
    public Integer getProductQuantityOfWarehouse(String warehouseId) {
        List<Stock> allByWarehouseId = stockRepository.findAllByWarehouseId(warehouseId);

        if (allByWarehouseId.isEmpty()) {
            return 0;
        }
        return allByWarehouseId.size();
    }

    public void notifyIfLowStock(String warehouseId) {
        List<StockDto> lowStocks = getLowStockItems(warehouseId);


        for (StockDto stock : lowStocks) {
            Warehouse warehouse = warehouseRepository.findById(stock.getWarehouseId()).get();

            System.out.println(
                    "⚠️ LOW STOCK: Product = " + stock.getProductName() +
                    ", Warehouse = " + warehouse.getName() +
                    ", Quantity = " + stock.getQuantity() +
                    ", Min Stock = " + stock.getMinStock()
            );
        }
    }

    @Override
    public MyResponse deleteStock(String id) {
        return null;
    }

    @Override
    public StockDto getStockById(String id) {
        return null;
    }

    @Override
    public Optional<Stock> getStockByProductNameNameAndByWarehouseName( String productName, String warehouseName) {
        Optional<Product> product = productRepository.findByName(productName);
        Optional<Warehouse> warehouse = warehouseRepository.findByName(warehouseName);

        if (product.isPresent() && warehouse.isPresent()) {
            return stockRepository.findByWarehouseAndProduct(warehouseName, productName );
        }
        return Optional.empty();
    }
}
