package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.nova.buildingcrm.model.entity.Supplier;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Optional<Supplier> findByName(@NotBlank(message = "Supplier name cannot be blank") String name);

    @Query("SELECT s FROM Supplier s")
    List<Supplier> getAll();
}
