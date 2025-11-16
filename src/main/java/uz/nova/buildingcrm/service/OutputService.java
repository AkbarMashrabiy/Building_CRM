package uz.nova.buildingcrm.service;

import uz.nova.buildingcrm.model.dto.OutputDtoResponse;
import uz.nova.buildingcrm.model.entity.AuthUser;
import uz.nova.buildingcrm.model.entity.Output;

import java.util.List;

public interface OutputService {

    List<OutputDtoResponse> getOutputByCashier(AuthUser cashier);
}
