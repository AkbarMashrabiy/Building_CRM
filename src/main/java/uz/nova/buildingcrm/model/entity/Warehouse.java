package uz.nova.buildingcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.nova.buildingcrm.model.base.BaseEntity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse extends BaseEntity {

    @Column(unique = true)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 255, message = "Warehouse name must be between 3 and 255 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size (min = 5, max = 255, message = "Warehouse name must be between 3 and 255 characters")
    private String address;

    private boolean active = true;
}
