package uz.nova.buildingcrm.service;

import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.UnitDto;
import uz.nova.buildingcrm.model.entity.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    MyResponse createUnit(UnitDto unit);
    MyResponse updateUnit(UnitDto unit);
    MyResponse deleteUnit(UnitDto unit);
    List<UnitDto> getAllUnits();
    Optional<Unit> getUnitById(String id);




}
