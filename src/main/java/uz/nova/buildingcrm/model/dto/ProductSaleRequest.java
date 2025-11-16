package uz.nova.buildingcrm.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProductSaleRequest {
    private String warehouseId;
    private String saleType;        // PAID or CREDIT
    private List<SaleItemRequest> items;


    private String customerName;
    private String phone;
    private LocalDate dueDate;
}