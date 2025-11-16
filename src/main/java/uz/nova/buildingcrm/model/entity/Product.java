package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.nova.buildingcrm.model.base.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product extends BaseEntity {

    @Column(unique = true)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "SKU cannot be blank")
    @Column(unique = true)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    @NotNull(message = "Unit cannot be null")
    private Unit unit;

    @NotNull(message = "Retail price cannot be blank")
    private BigDecimal retailPrice;

    @NotNull(message = "Wholesale price cannot be blank")
    private BigDecimal wholesalePrice;

    @NotBlank(message = "Barcode cannot be blank")
    private String barcode;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    private Boolean active = true;
}
