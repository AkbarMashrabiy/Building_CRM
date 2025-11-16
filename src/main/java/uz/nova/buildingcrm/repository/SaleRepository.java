package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.nova.buildingcrm.model.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, String> {

}
