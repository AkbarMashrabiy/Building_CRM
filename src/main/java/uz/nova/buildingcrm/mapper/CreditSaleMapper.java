package uz.nova.buildingcrm.mapper;

import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.CreditSaleDto;
import uz.nova.buildingcrm.model.entity.CreditSale;

@Component
public class CreditSaleMapper{

    public CreditSaleDto toDTO(CreditSale entity) {
        CreditSaleDto dto = new CreditSaleDto();
        dto.setId(entity.getId());
        dto.setCreditSaleId(entity.getId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setCustomerPhone(entity.getPhone());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setPaidAmount(entity.getPaidAmount());
        dto.setPurchaseDate(entity.getPurchaseDate());
        dto.setDueDate(entity.getDueDate());
        dto.setStatus(entity.getStatus().toString());
        return dto;
    }





}
