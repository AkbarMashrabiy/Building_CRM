package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
    boolean existsByName(String name);
    Optional<Warehouse> findById(String id);

    Optional<Warehouse> findByName(String name);
}

