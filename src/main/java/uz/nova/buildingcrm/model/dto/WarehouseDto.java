package uz.nova.buildingcrm.model.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseDto {

    private String id;

    @NotBlank(message = "Warehouse name cannot be blank")
    private String name;

    @NotBlank(message = "Warehouse email cannot be blank")
    private String address;
}
