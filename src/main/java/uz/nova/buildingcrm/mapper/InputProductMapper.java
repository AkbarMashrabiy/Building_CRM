package uz.nova.buildingcrm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.InputProductCreateDto;
import uz.nova.buildingcrm.model.dto.InputProductDto;
import uz.nova.buildingcrm.model.entity.*;
import uz.nova.buildingcrm.validator.SupplierValidator;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InputProductMapper {
    private final SupplierValidator supplierValidator;

    public InputProduct toEntityForCreate(InputProductCreateDto dto, Supplier supplier, Warehouse warehouse, Product product, Unit unit) {

        InputProduct entity = new InputProduct();
        entity.setQuantity(dto.getQuantity());
        entity.setCurrency(dto.getCurrencyName());
        entity.setDate(LocalDateTime.now());
        entity.setPrice(dto.getRetailPrice());
        entity.setOosPercentage(dto.getOosPercentage());
        entity.setMinStock(dto.getMinStock());
        entity.setSupplier(supplier);
        entity.setSupplier(supplier);
        entity.setWarehouse(warehouse);
        entity.setProduct(product);
        entity.setUnit(unit);

        return entity;
    }

    public InputProduct toEntity(InputProductDto dto, Supplier supplier, Warehouse warehouse, Product product, Unit unit) {
        InputProduct entity = new InputProduct();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setCurrency(dto.getCurrencyName());
        entity.setDate(dto.getDate());
        entity.setPrice(dto.getRetailPrice());
        entity.setOosPercentage(dto.getOosPercentage());
        entity.setMinStock(dto.getMinStock());
        entity.setSupplier(supplier);
        entity.setWarehouse(warehouse);
        entity.setProduct(product);
        entity.setUnit(unit);
        return entity;
    }


    


    public InputProductDto toDto(InputProduct entity) {
        InputProductDto dto = new InputProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getProduct().getName());
        dto.setQuantity(entity.getQuantity());
        dto.setCurrencyName(entity.getCurrency());
        dto.setUnitId(entity.getUnit().getId());
        dto.setDate(entity.getDate());
        dto.setRetailPrice(entity.getPrice());
        dto.setOosPercentage(entity.getOosPercentage());
        dto.setBrand(entity.getProduct().getBrand());
        dto.setBarcode(entity.getProduct().getBarcode());
        dto.setMinStock(entity.getMinStock());
        dto.setSku(entity.getProduct().getSku());
        dto.setWarehouseId(entity.getWarehouse().getId());
        dto.setWholesalePrice(entity.getPrice());
        return dto;
    }
}














