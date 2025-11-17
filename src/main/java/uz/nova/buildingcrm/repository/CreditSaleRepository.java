package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.nova.buildingcrm.model.entity.CreditSale;

import java.util.List;

public interface CreditSaleRepository extends JpaRepository<CreditSale, String> {


    @Query("SELECT cs FROM CreditSale cs WHERE cs.sale.cashier.id = :cashierId AND cs.status = 'UNPAID' or cs.status = 'CREDIT'")
    List<CreditSale> findAllUnpaidCreditSalesByCashierId(String cashierId);
}
