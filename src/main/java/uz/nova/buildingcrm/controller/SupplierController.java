package uz.nova.buildingcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.SupplierDto;
import uz.nova.buildingcrm.service.SupplierService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;


    @GetMapping("")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<MyResponse> createSupplier(@RequestBody SupplierDto supplier) {
        return ResponseEntity.ok(supplierService.add(supplier));
    }


}
