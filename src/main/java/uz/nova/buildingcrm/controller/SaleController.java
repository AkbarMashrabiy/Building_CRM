package uz.nova.buildingcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.OutputDtoResponse;
import uz.nova.buildingcrm.model.dto.ProductSaleRequest;
import uz.nova.buildingcrm.service.AuthUserService;
import uz.nova.buildingcrm.service.OutputService;
import uz.nova.buildingcrm.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
public class SaleController {
    private final OutputService outputService;
    private final SaleService saleService;
    private final AuthUserService authUserService;

    @PostMapping("")
    public ResponseEntity<MyResponse> sell(@RequestBody ProductSaleRequest dto) {
        return ResponseEntity.ok(saleService.makeSale(dto));
    }

    @GetMapping("/checks")
    public ResponseEntity<List<OutputDtoResponse>> checkSale() {
        return ResponseEntity.ok(outputService.getOutputByCashier(authUserService.getCurrentUser()));
    }
}