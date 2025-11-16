package uz.nova.buildingcrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nova.buildingcrm.mapper.OutputProductMapper;
import uz.nova.buildingcrm.model.dto.OutputDtoResponse;
import uz.nova.buildingcrm.model.entity.AuthUser;
import uz.nova.buildingcrm.model.entity.Output;
import uz.nova.buildingcrm.repository.OutputRepository;
import uz.nova.buildingcrm.service.OutputService;

    import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OutputServiceImpl implements OutputService {
    private final OutputRepository outputRepository;
    private final OutputProductMapper outputProductMapper;

    @Override
    public List<OutputDtoResponse> getOutputByCashier(AuthUser cashier) {
        List<Output> outputs = outputRepository.findByCashier(cashier);

        return outputs.stream()
                .map(output -> {
                    OutputDtoResponse dto = outputProductMapper.toOutputDtoResponse(output);
                    System.out.println("Products size: " + output.getProducts().size());
                    return dto;
                })
                .toList();
    }
}
