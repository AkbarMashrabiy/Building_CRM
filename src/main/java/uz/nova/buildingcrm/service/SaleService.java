package uz.nova.buildingcrm.service;

import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.ProductSaleRequest;
import uz.nova.buildingcrm.model.entity.CreditSale;

import java.util.List;

public interface SaleService {
    MyResponse makeSale(ProductSaleRequest request);
}
