package uz.nova.buildingcrm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.nova.buildingcrm.model.dto.UnitDto;
import uz.nova.buildingcrm.service.UnitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/units")
public class UnitController {
    private final UnitService unitService;

    @RequestMapping("")
    public ResponseEntity<List<UnitDto>> getAllUnits() {
        return ResponseEntity.ok(unitService.getAllUnits());
    }

    @PostMapping("/add")
    public ResponseEntity<UnitDto> addUnit(@RequestBody UnitDto unitDto) {
        unitService.createUnit(unitDto);
        return ResponseEntity.ok(unitDto);
    }

}
