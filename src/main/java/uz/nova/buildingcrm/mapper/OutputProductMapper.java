package uz.nova.buildingcrm.mapper;

import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.OutputDtoResponse;
import uz.nova.buildingcrm.model.dto.OutputProductResponseDto;
import uz.nova.buildingcrm.model.entity.Output;
import uz.nova.buildingcrm.model.entity.OutputProduct;
import uz.nova.buildingcrm.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutputProductMapper {

    public OutputProduct toOutputProduct(Product product, Output output, Double amount) {

        return OutputProduct.builder()
                .product(product)
                .productName(product.getName())
                .productPrice(product.getRetailPrice())
                .output(output)
                .amount(amount)
                .price(product.getRetailPrice().doubleValue() * amount)
                .build();
    }

    public List<OutputProductResponseDto> toOutputProductResponseDto(List<OutputProduct> outputProducts) {
        return outputProducts.stream().map(product -> {
            OutputProductResponseDto dto = new OutputProductResponseDto();
            dto.setName(product.getProductName());
            dto.setQuantity(product.getAmount().intValue());

            double totalItemPrice = product.getPrice() * product.getAmount();
            dto.setTotalPrice((int) totalItemPrice);

            return dto;
        }).toList();
    }


    public OutputDtoResponse toOutputDtoResponse(Output output) {
        OutputDtoResponse dto = new OutputDtoResponse();
        dto.setId(output.getId());
        dto.setDate(output.getDate());
        dto.setCashierName(output.getCashier().getFullName());
        dto.setCurrencyName(output.getCurrency().toString());

        double total = output.getProducts().stream()
                .mapToDouble(op -> op.getPrice() * op.getAmount())
                .sum();

        dto.setTotalPrice((int) total);
        dto.setProducts(toOutputProductResponseDto(output.getProducts()));

        return dto;
    }
}








