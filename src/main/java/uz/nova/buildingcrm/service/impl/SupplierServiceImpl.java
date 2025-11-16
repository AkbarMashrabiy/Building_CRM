package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nova.buildingcrm.mapper.SupplierMapper;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.SupplierDto;
import uz.nova.buildingcrm.model.entity.Supplier;
import uz.nova.buildingcrm.repository.SupplierRepository;
import uz.nova.buildingcrm.service.SupplierService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    @Transactional
    public MyResponse add(SupplierDto dto) {
        Optional<Supplier> byName = repository.findByName(dto.getName());
        if (byName.isPresent()) {
            return MyResponse.SUPPLIER_NAME_ALREADY_EXISTS;
        }
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        repository.save(supplier);
        return MyResponse.SUCCESSFULLY_CREATED;
    }

    @Override
    public List<SupplierDto> getAll() {
        return repository.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MyResponse delete(SupplierDto dto) {
        Optional<Supplier> byId = repository.findById(dto.getId());
        if (byId.isPresent()) {
            Supplier supplier = byId.get();
            supplier.setActive(false);
            repository.save(supplier);
            return MyResponse.SUCCESSFULLY_DELETED;
        }
        return MyResponse.SUPPLIER_NOT_FOUND;
    }

    @Override
    @Transactional
    public MyResponse update(SupplierDto dto) {
        Optional<Supplier> byId = repository.findById(dto.getId());
        if (byId.isPresent()) {
            Supplier supplier = byId.get();
            supplier.setId(dto.getId());
            supplier.setName(dto.getName());
            supplier.setPhone(dto.getPhone());
            repository.save(supplier);
            return MyResponse.SUCCESSFULLY_UPDATED;
        }
        return MyResponse.SUPPLIER_NOT_FOUND;
    }

    @Override
    public Optional<SupplierDto> getById(String id) {
        return repository.findById(id).map(mapper::toDto);
    }
}
