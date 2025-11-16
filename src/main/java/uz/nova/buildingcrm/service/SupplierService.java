package uz.nova.buildingcrm.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.SupplierDto;
import uz.nova.buildingcrm.model.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    MyResponse add(SupplierDto supplier);
    List<SupplierDto> getAll();
    MyResponse delete(SupplierDto supplier);
    MyResponse update(SupplierDto supplier);
    Optional<SupplierDto> getById(String id);
}
