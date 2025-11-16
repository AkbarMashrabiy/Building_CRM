package uz.nova.buildingcrm.model.dto;

import lombok.*;
import uz.nova.buildingcrm.model.enums.CurrencyName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputProductCreateDto {

    @NotBlank(message = "Product name cannot be blank")
    private String name;  // Mahsulot nomi, frontenddagi "Nomi" field

    @NotBlank(message = "SKU cannot be blank")
    private String sku;   // Mahsulot SKU, frontenddagi "SKU" field

    @NotNull(message = "Unit ID cannot be null")
    private CurrencyName currencyName; // Pul birligi ID (dropdown dan tanlanadi)

    private String unitId; // O'lchov birligi ID (dropdown dan tanlanadi)

    @NotNull(message = "Retail price cannot be blank")
    private BigDecimal retailPrice; // Chakana narx (UZS)

    @NotNull(message = "Wholesale price cannot be blank")
    private BigDecimal wholesalePrice; // Ulgurji narx (UZS)

    @NotBlank(message = "Barcode cannot be blank")
    private String barcode; // Mahsulot shtrix-kodi

    @NotBlank(message = "Brand cannot be blank")
    private String brand; // Mahsulot brendi, frontenddagi "Brend"

    @NotNull(message = "Warehouse ID cannot be null")
    private String warehouseId; // Ombor ID, frontenddagi "Ombor" dropdown

    private Integer quantity; // Mahsulot qoldiq, frontenddagi "Qoldiq" field

    @NotNull(message = "Minimum stock cannot be blank")
    private Integer minStock; // Minimal qoldiq, frontenddagi "Minimal qoldiq"

    @NotNull(message = "OOS percentage cannot be blank")
    private Integer oosPercentage; // QQS stavkasi (%), frontenddagi "QQS stavkasi"
}
