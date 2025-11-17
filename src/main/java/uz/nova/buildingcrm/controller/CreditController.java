package uz.nova.buildingcrm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.nova.buildingcrm.model.dto.CreditSaleDto;
import uz.nova.buildingcrm.service.AuthUserService;
import uz.nova.buildingcrm.service.CreditService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/credits")
public class CreditController {
    private final CreditService creditService;
    private final AuthUserService authUserService;

    @GetMapping("")
    public ResponseEntity<List<CreditSaleDto>> getAll() {
        String id = authUserService.getCurrentUser().getId();
        return ResponseEntity.ok(creditService.getUnpaidCreditSalesByCashierId(id));
    }
}
