package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import uz.nova.buildingcrm.model.enums.CurrencyName;
import uz.nova.buildingcrm.model.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InputProduct extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product cannot be blank")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @NotNull(message = "Warehouse cannot be blank")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @NotNull(message = "supplier can't be null")
    private Supplier supplier;

    @NotNull(message = "currency can't be null")
    @Enumerated(EnumType.STRING)
    private CurrencyName currency;

    @NotNull(message = "unit can't be null")
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @NotNull(message = "date can't be null")
    private LocalDateTime date;

    @NotNull(message = "Amount cannot be blank")
    private Integer quantity;

    @NotNull(message = "Price cannot be blank")
    private BigDecimal price;

    @NotNull(message = "OOS percentage cannot be blank")
    private Integer oosPercentage;

    @NotNull(message = "Min stock cannot be blank")
    private Integer minStock;
}
