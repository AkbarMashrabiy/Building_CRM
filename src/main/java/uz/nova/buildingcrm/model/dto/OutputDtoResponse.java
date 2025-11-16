package uz.nova.buildingcrm.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutputDtoResponse {
    @NotNull(message = "cashier can't be null")
    private String cashierName;
    private String id;
    private String currencyName;
    private List<OutputProductResponseDto> products;
    private LocalDateTime date;
    private Integer totalPrice;

}
