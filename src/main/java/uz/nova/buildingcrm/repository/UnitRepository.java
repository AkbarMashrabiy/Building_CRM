package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.Unit;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, String> {


    Optional<Unit> findByName(String name);
}
