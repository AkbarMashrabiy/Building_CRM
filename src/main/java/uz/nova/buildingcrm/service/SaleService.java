package uz.nova.buildingcrm.service;

import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.ProductSaleRequest;

public interface SaleService {
    MyResponse makeSale(ProductSaleRequest request);
}
