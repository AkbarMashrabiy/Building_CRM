package uz.nova.buildingcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.nova.buildingcrm.model.entity.Product;
import uz.nova.buildingcrm.model.entity.Stock;
import uz.nova.buildingcrm.model.entity.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    List<Stock> findByQuantityLessThanEqual(BigDecimal quantity);

    @Query("SELECT s FROM Stock s WHERE s.quantity <= s.minStock AND s.warehouse.id = :warehouseId")
    List<Stock> findLowStockItems(@Param("warehouseId") String warehouseId);

//    @Query("""
//        select s from Stock s
//        join fetch s.product p
//        join fetch s.warehouse w
//        left join fetch p.barcode b
//        where w.id = :warehouseId
//        """)
//    List<Stock> findAllByWarehouseIdWithProductAndBarcodes(@Param("warehouseId") String warehouseId);


    @Query("""
        SELECT s FROM Stock s 
        WHERE s.warehouse.name = :warehouseName 
          AND s.product.name = :productName
        """)
    Optional<Stock> findByWarehouseAndProduct(
            @Param("warehouseName") String warehouseName,
            @Param("productName") String productName
    );


    Optional<Stock> findByProductNameAndWarehouseId(String productId, String warehouseId);

    List<Stock> findAllByWarehouseId(String warehouseId);
}



