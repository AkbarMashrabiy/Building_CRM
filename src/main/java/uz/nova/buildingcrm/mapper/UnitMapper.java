package uz.nova.buildingcrm.mapper;

import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.UnitDto;
import uz.nova.buildingcrm.model.entity.Unit;

@Component
public class UnitMapper {

    public UnitDto toDto(Unit unit) {
        UnitDto dto = new UnitDto();
        dto.setId(unit.getId());
        dto.setName(unit.getName());
        dto.setSymbol(unit.getSymbol());
        dto.setUnitCategory(unit.getCategory());
        dto.setCoefficient(unit.getCoefficient());
        return dto;
    }

    public Unit toEntity(UnitDto dto) {
        Unit entity = new Unit();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setSymbol(dto.getSymbol());
        entity.setCategory(dto.getUnitCategory());
        entity.setCoefficient(dto.getCoefficient());
        return entity;
    }

}
