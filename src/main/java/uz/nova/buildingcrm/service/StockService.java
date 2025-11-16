package uz.nova.buildingcrm.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.StockDto;
import uz.nova.buildingcrm.model.entity.Product;
import uz.nova.buildingcrm.model.entity.Stock;
import uz.nova.buildingcrm.model.entity.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StockService {

    MyResponse updateQuantity(String id, Integer quantity);
    List<StockDto> getAllByWarehouseId(String warehouseId);
    BigDecimal getTotalPriceOfWarehouse(String warehouseId);
    Integer getProductQuantityOfWarehouse(String warehouseId);
    List<StockDto> getLowStockItems(String warehouseId);
    void notifyIfLowStock(String warehouseId);
    MyResponse deleteStock(String id);
    StockDto getStockById(String id);
    Optional<Stock> getStockByProductNameNameAndByWarehouseName(String ProductName, String warehouseName);


}
