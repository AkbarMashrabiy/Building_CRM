package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.nova.buildingcrm.model.base.BaseEntity;
import uz.nova.buildingcrm.model.enums.UnitCategory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Unit extends BaseEntity {

    @Column(unique = true)
    @NotBlank
    private String name;

    @NotBlank
    private String symbol;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be null")
    private UnitCategory category;

    @NotNull(message = "Coefficient cannot be null")
    private Double coefficient;

    private boolean active = true;
}