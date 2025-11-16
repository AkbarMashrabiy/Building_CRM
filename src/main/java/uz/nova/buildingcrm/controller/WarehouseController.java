package uz.nova.buildingcrm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.WarehouseDto;
import uz.nova.buildingcrm.service.WarehouseService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {
    private final WarehouseService service;

    @GetMapping("")
    public ResponseEntity<List<WarehouseDto>> getWarehouses() {
        List<WarehouseDto> allWarehouses = service.getAllWarehouses();
        if (allWarehouses == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allWarehouses);
    }

//    @PreAuthorize("hasAnyRole('MANAGER')")
    @PostMapping("/add")
    public ResponseEntity<MyResponse> createWarehouse(@RequestBody @Valid WarehouseDto warehouseDto) {

        MyResponse myResponse = service.addWarehouse(warehouseDto);
        if (myResponse == MyResponse.SUCCESSFULLY_CREATED) {
            return ResponseEntity.ok(myResponse);
        }
        return ResponseEntity.status(400).body(MyResponse.FAILED_TO_CREATE);
    }

        @GetMapping("/{id}")
        public ResponseEntity<WarehouseDto> getWarehouseById(@PathVariable("id") @Valid String id) {
            Optional<WarehouseDto> warehouseDto = service.getWarehouseById(id);
            return warehouseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponse> deleteWarehouse(@PathVariable("id") @Valid String id) {
        return ResponseEntity.ok(service.deleteWarehouse(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyResponse> updateWarehouse(@PathVariable("id") @Valid String id, @RequestBody @Valid WarehouseDto warehouseDto) {
        return ResponseEntity.ok(service.updateWarehouse(id, warehouseDto));
    }

}
