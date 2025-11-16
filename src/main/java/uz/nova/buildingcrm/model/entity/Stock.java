package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.nova.buildingcrm.model.base.BaseEntity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product cannot be null")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @NotNull(message = "Warehouse cannot be null")
    private Warehouse warehouse;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Min stock cannot be null")
    private Integer minStock;

    private Boolean active = true;
}
