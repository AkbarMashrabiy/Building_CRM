package uz.nova.buildingcrm.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutputProductResponseDto {
    private String name;
    private Integer quantity;
    private Integer totalPrice;
}
