package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.nova.buildingcrm.model.base.BaseEntity;
import uz.nova.buildingcrm.model.enums.SaleStatus;

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
@ToString
public class CreditSale extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @NotNull(message = "Sale cannot be null")
    private Sale sale;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    @NotNull(message = "Total amount cannot be null")
    private BigDecimal totalAmount;

    @NotNull(message = "Paid amount cannot be null")
    private BigDecimal paidAmount;

    @NotNull(message = "Remaining amount cannot be null")
    private LocalDate purchaseDate;

    @NotNull(message = "Due date cannot be null")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private SaleStatus status;
}
