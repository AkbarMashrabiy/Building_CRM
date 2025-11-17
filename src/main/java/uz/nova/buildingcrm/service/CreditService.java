package uz.nova.buildingcrm.service;

import uz.nova.buildingcrm.model.dto.CreditSaleDto;
import uz.nova.buildingcrm.model.entity.CreditSale;

import java.util.List;

public interface CreditService {
    List<CreditSaleDto> getUnpaidCreditSalesByCashierId(String customerId);
}
