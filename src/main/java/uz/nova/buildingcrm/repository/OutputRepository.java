package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.AuthUser;
import uz.nova.buildingcrm.model.entity.Output;

import java.util.List;

public interface OutputRepository extends JpaRepository<Output, String> {

    List<Output> findByCashier(AuthUser cashier);
}
