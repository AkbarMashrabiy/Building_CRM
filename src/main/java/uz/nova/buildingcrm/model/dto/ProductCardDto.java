package uz.nova.buildingcrm.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCardDto {

    private String productId;

    private String name;
    private String sku;

    private String unit_id;

    private BigDecimal retailPrice;
    private BigDecimal wholesalePrice;
    private String barcode;
    private String brand;
    private String warehouseName;

    private Integer quantity;
    private Integer minStock;
    private boolean lowStock;
}
