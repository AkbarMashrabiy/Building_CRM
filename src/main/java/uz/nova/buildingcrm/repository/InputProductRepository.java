package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct , String> {

}
