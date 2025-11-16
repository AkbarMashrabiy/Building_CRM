package uz.nova.buildingcrm.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.WarehouseDto;
import uz.nova.buildingcrm.model.entity.Warehouse;

@Component
@RequiredArgsConstructor
public class WarehouseMapper {
    public WarehouseDto toDto(Warehouse warehouse) {
        WarehouseDto dto = new WarehouseDto();
        dto.setId(warehouse.getId());
        dto.setName(warehouse.getName());
        dto.setAddress(warehouse.getAddress());
        return dto;
    }

    public Warehouse toEntity(WarehouseDto dto) {
        Warehouse entity = new Warehouse();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setActive(true);
        return entity;
    }
}



