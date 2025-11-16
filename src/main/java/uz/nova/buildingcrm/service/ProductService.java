package uz.nova.buildingcrm.service;


import uz.nova.buildingcrm.model.dto.InputProductDto;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.ProductCardDto;
import uz.nova.buildingcrm.model.dto.InputProductCreateDto;

import java.util.List;

public interface ProductService {
    List<ProductCardDto> getProductsForWarehouse(String warehouseId);
    MyResponse createProduct(InputProductCreateDto product);
    MyResponse addQuantityById(String inputId, Integer quantity);
    MyResponse updateProduct(String productId, InputProductDto product);
    MyResponse deleteProduct(String productId);
    List<InputProductDto> getAll();
}
