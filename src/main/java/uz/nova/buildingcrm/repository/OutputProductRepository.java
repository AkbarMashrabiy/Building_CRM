package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, String> {

}
