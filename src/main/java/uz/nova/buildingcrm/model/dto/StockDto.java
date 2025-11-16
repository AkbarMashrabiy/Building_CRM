package uz.nova.buildingcrm.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockDto {

    private String id;

    @NotBlank(message = "Product name cannot be null")
    private String warehouseId;

    @NotBlank(message = "Product name cannot be null")
    private String unitName;

    @NotBlank(message = "Product name cannot be null")
    private String productName;

    @NotBlank(message = "Product SKU cannot be null")
    private String productSKU;

    @NotBlank(message = "Product Quantity cannot be null")
    private Integer quantity;

    @NotBlank(message = "Product minStock cannot be null")
    private Integer minStock;

    private Boolean active = true;

}
