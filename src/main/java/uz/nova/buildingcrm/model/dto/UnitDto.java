package uz.nova.buildingcrm.model.dto;

import lombok.*;
import uz.nova.buildingcrm.model.enums.UnitCategory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnitDto {

    private String id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Symbol cannot be blank")
    private String symbol;
    @NotNull(message = "Category cannot be null")
    private UnitCategory unitCategory;
    @NotNull(message = "Coefficient cannot be null")
    private Double coefficient;
}
