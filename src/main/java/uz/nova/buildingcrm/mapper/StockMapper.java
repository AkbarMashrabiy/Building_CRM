package uz.nova.buildingcrm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.StockDto;
import uz.nova.buildingcrm.model.entity.Product;
import uz.nova.buildingcrm.model.entity.Stock;
import uz.nova.buildingcrm.model.entity.Warehouse;
import uz.nova.buildingcrm.repository.ProductRepository;
import uz.nova.buildingcrm.repository.WarehouseRepository;

@Component
@RequiredArgsConstructor
public class StockMapper {
    private final ProductRepository stockRepository;
    private final WarehouseRepository warehouseRepository;


    public StockDto toDto(Stock stock) {
        StockDto dto = new StockDto();
        dto.setId(stock.getId());
        dto.setMinStock(stock.getMinStock());
        dto.setQuantity(stock.getQuantity());
        dto.setUnitName(stock.getProduct().getUnit().getName());
        dto.setProductSKU(stock.getProduct().getSku());
        dto.setProductName(stock.getProduct().getName());
        dto.setWarehouseId(stock.getWarehouse().getId());
        return dto;
    }

    public Stock toEntity(StockDto entity) {
        Stock stock = new Stock();
        stock.setId(entity.getId());
        stock.setMinStock(entity.getMinStock());
        stock.setQuantity(entity.getQuantity());
        stock.setActive(entity.getActive());
        stock.setProduct(stockRepository.findByName(entity.getProductName()).get());
        stock.setWarehouse(warehouseRepository.findById(entity.getWarehouseId()).get());
        return stock;
    }

}








