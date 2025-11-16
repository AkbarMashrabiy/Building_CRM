package uz.nova.buildingcrm.mapper;

import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.SupplierDto;
import uz.nova.buildingcrm.model.entity.Supplier;


@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierDto dto) {
        Supplier supplier = new Supplier();
        if (dto.getId() != null) {
            supplier.setId(dto.getId());
        }
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setActive(dto.getActive());
        return supplier;
    }

    public SupplierDto toDto(Supplier entity) {
        SupplierDto dto = new SupplierDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setActive(entity.getActive());
        return dto;
    }
}
