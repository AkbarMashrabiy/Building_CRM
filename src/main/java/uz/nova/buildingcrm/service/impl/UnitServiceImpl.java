package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nova.buildingcrm.mapper.UnitMapper;
import uz.nova.buildingcrm.model.dto.MyResponse;
import uz.nova.buildingcrm.model.dto.UnitDto;
import uz.nova.buildingcrm.model.entity.Unit;
import uz.nova.buildingcrm.repository.UnitRepository;
import uz.nova.buildingcrm.service.UnitService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    @Transactional
    public MyResponse createUnit(UnitDto dto) {
        Optional<Unit> exist = unitRepository.findById(dto.getId());
        if (exist.isPresent()) {
            return MyResponse.UNIT_NAME_EXISTS;
        }
        Unit entity = unitMapper.toEntity(dto);
        unitRepository.save(entity);
        return MyResponse.SUCCESSFULLY_CREATED;
    }

    @Override
    @Transactional
    public MyResponse updateUnit(UnitDto dto) {
        Optional<Unit> exist = unitRepository.findById(dto.getId());
        if (exist.isPresent()) {
            Unit entity = unitMapper.toEntity(dto);
            unitRepository.save(entity);
            return MyResponse.SUCCESSFULLY_UPDATED;
        }
        return MyResponse.UNIT_NOT_FOUND;
    }

    @Override
    @Transactional
    public MyResponse deleteUnit(UnitDto unit) {
        Optional<Unit> exist = unitRepository.findById(unit.getId());
        if (exist.isPresent()) {
            Unit entity = unitMapper.toEntity(unit);
            entity.setActive(false);
            unitRepository.save(entity);
            return MyResponse.SUCCESSFULLY_DELETED;
        }
        return MyResponse.UNIT_NOT_FOUND;
    }

    @Override
    public List<UnitDto> getAllUnits() {
        return unitRepository.findAll().stream().map(unitMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Unit> getUnitById(String id) {
        return unitRepository.findById(id);
    }

}
