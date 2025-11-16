package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nova.buildingcrm.mapper.WarehouseMapper;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.WarehouseDto;
import uz.nova.buildingcrm.model.entity.Warehouse;
import uz.nova.buildingcrm.repository.WarehouseRepository;
import uz.nova.buildingcrm.service.WarehouseService;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository repository;
    private final WarehouseMapper mapper;

    @Override
    @Transactional
    public MyResponse addWarehouse(WarehouseDto warehouse) {
        Warehouse entity = mapper.toEntity(warehouse);
        if (entity == null | repository.existsByName(warehouse.getName())){
            return MyResponse.FAILED_TO_CREATE;
        }
        repository.save(entity);

        return MyResponse.SUCCESSFULLY_CREATED;
    }

    @Override
    public Optional<WarehouseDto> getWarehouseById(String id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<WarehouseDto> getWarehouseByName(String name) {
        return repository.findByName(name).map(mapper::toDto);
    }

    @Override
    public List<WarehouseDto> getAllWarehouses() {
        List<WarehouseDto> list = repository.findAll().stream().map(mapper::toDto).toList();
        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    @Transactional
    public MyResponse deleteWarehouse(String id) {
        if (repository.existsById(id)){
            Warehouse warehouse = repository.findById(id).get();
            warehouse.setActive(false);
            repository.save(warehouse);
            return MyResponse.SUCCESSFULLY_DELETED;
        }
        return MyResponse.CANT_DELETE;
    }

    @Override
    @Transactional
    public MyResponse updateWarehouse(String id, WarehouseDto warehouseDto) {
        if (repository.existsById(id)){
            Warehouse warehouse = repository.findById(id).get();
            warehouse.setName(warehouseDto.getName());
            warehouse.setAddress(warehouseDto.getAddress());
            warehouse.setActive(true);
            repository.save(warehouse);
            return MyResponse.SUCCESSFULLY_UPDATED;
        }
    return MyResponse.WAREHOUSE_NOT_FOUND;
    }
}
