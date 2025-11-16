package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import uz.nova.buildingcrm.model.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import uz.nova.buildingcrm.model.enums.CurrencyName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Output extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @NotNull(message = "warehouse can't be null")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    @NotNull(message = "cashier can't be null")
    private AuthUser cashier;

    @NotNull(message = "currency can't be null")
    @Enumerated(EnumType.STRING)
    private CurrencyName currency;

    @OneToMany(mappedBy = "output",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @NotNull(message = "products can't be null")
    private List<OutputProduct> products;

    @NotNull(message = "date can't be null")
    private LocalDateTime date;
}