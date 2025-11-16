package uz.nova.buildingcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.nova.buildingcrm.model.dto.InputProductCreateDto;
import uz.nova.buildingcrm.model.dto.InputProductDto;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.ProductCardDto;
import uz.nova.buildingcrm.service.ProductService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productCardService;

    @GetMapping("")
    public ResponseEntity<List<InputProductDto>> getAllProducts() {
        return ResponseEntity.ok(productCardService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<MyResponse> addProduct(@RequestBody InputProductCreateDto dto) {
        return ResponseEntity.ok(productCardService.createProduct(dto));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<ProductCardDto>> getProducts(@PathVariable String warehouseId) {
        return ResponseEntity.ok(productCardService.getProductsForWarehouse(warehouseId));
    }


}
