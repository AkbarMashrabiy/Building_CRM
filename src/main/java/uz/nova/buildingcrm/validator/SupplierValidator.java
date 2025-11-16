package uz.nova.buildingcrm.validator;

import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.entity.Supplier;
import uz.nova.buildingcrm.repository.SupplierRepository;

import java.util.NoSuchElementException;

@Component
public class SupplierValidator {

    private final SupplierRepository supplierRepository;
    public SupplierValidator(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier getSupplier(String name) {
        return supplierRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Supplier not found with NAME "  + name) );
    }







}
