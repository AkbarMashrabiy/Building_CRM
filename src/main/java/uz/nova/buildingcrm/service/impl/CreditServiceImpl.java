package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.nova.buildingcrm.mapper.CreditSaleMapper;
import uz.nova.buildingcrm.model.dto.CreditSaleDto;
import uz.nova.buildingcrm.model.entity.CreditSale;
import uz.nova.buildingcrm.repository.CreditSaleRepository;
import uz.nova.buildingcrm.service.CreditService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditSaleRepository creditSaleRepository;
    private final CreditSaleMapper creditSaleMapper;
    @Override
    public List<CreditSaleDto> getUnpaidCreditSalesByCashierId(String cashierId) {
        List<CreditSale> sales = creditSaleRepository.findAllUnpaidCreditSalesByCashierId(cashierId);
        List<CreditSaleDto> list = new ArrayList<>();
        for (CreditSale sale : sales) {
            list.add(creditSaleMapper.toDTO(sale));
        }
        return list;
    }
}
