package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uz.nova.buildingcrm.model.base.BaseEntity;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OutputProduct extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product cannot be blank")
    private Product product;

    private String productName;
    private BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name = "output_id", nullable = false)
    @NotNull(message = "Output cannot be blank")
    private Output output;

    @NotNull(message = "Amount cannot be blank")
    private Double amount;

    @NotNull(message = "Price cannot be blank")
    private Double price;
}
