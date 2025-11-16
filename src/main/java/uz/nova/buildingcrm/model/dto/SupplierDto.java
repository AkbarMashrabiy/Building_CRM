package uz.nova.buildingcrm.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDto {
    private String id;

    @NotBlank(message = "Supplier name cannot be blank")
    private String name;

    @NotBlank(message = "Supplier phone number cannot be blank")
    @Size(min = 12, max = 12, message = "Supplier phone number must be 12 digits")
    private String phone;

    private Boolean active = true;


}
