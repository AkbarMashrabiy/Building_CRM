package uz.nova.buildingcrm.service;

import uz.nova.buildingcrm.model.dto.WarehouseDto;
import uz.nova.buildingcrm.model.dto.MyResponse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    MyResponse addWarehouse(WarehouseDto warehouse);
    Optional<WarehouseDto> getWarehouseById(String id);
    Optional<WarehouseDto> getWarehouseByName(String name);
    List<WarehouseDto> getAllWarehouses();
    MyResponse deleteWarehouse(String id);
    MyResponse updateWarehouse(String id, WarehouseDto warehouseDto);
}
