package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.nova.buildingcrm.model.base.BaseEntity;
import uz.nova.buildingcrm.model.enums.SaleStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @Valid
    @NotNull(message = "Warehouse cannot be null")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    @NotNull(message = "Customer cannot be null")
    private AuthUser cashier;

    @NotNull(message = "Total amount cannot be null")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be blank")
    private SaleStatus status;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;
}
