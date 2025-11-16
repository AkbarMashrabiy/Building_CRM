package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.Product;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByName(@NotBlank(message = "Product name cannot be blank") String name);
}
