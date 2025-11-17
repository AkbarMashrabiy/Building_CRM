package uz.nova.buildingcrm.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditSaleDto {
    private String id;
    private String creditSaleId;
    private String customerName;
    private String customerPhone;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private LocalDate purchaseDate;
    private LocalDate dueDate;
    private String status;
}
