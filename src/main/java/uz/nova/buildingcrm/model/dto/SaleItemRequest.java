package uz.nova.buildingcrm.model.dto;

import lombok.Data;

@Data
public class SaleItemRequest {

    private String productName;   // Qaysi mahsulot sotilmoqda
    private Integer amount;         // Nechta dona
//    private Integer price;       // Bitta donasining narxi
}