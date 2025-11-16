package uz.nova.buildingcrm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.nova.buildingcrm.model.dto.StockDto;
import uz.nova.buildingcrm.service.StockService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping("/{warehouseId}")
    public ResponseEntity<List<StockDto>> getAllStocksByWarehouse(@PathVariable("warehouseId") @Valid String warehouseId) {
        return ResponseEntity.ok(stockService.getAllByWarehouseId(warehouseId));
    }

    @GetMapping("/total-price/{warehouseId}")
    public ResponseEntity<BigDecimal> getTotalPriceByWarehouseId(@PathVariable("warehouseId") @Valid String warehouseId) {
        return ResponseEntity.ok(stockService.getTotalPriceOfWarehouse(warehouseId));
    }

    @GetMapping("/total-product/{warehouseId}")
    public ResponseEntity<Integer> getTotalProductQuantityByWarehouseId(@PathVariable("warehouseId") @Valid String warehouseId) {
        return ResponseEntity.ok(stockService.getProductQuantityOfWarehouse(warehouseId));
    }

    @GetMapping("/low-stocks/{warehouseId}")
    public ResponseEntity<List<StockDto>> getLowStackByWarehouseId(@PathVariable("warehouseId") @Valid String warehouseId) {
        return ResponseEntity.ok(stockService.getLowStockItems(warehouseId));
    }







}
