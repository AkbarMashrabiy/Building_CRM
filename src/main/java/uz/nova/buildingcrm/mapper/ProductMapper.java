package uz.nova.buildingcrm.mapper;

import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.dto.ProductCardDto;
import uz.nova.buildingcrm.model.dto.InputProductCreateDto;
import uz.nova.buildingcrm.model.entity.*;

import java.util.List;

@Component
public class ProductMapper {

    public ProductCardDto toDto(Stock stock) {

        Product p = stock.getProduct();

        boolean lowStock = stock.getQuantity()
                .compareTo(
                        stock.getMinStock()
                ) < 0;

        ProductCardDto dto = new ProductCardDto();
        dto.setProductId(p.getId());
        dto.setName(p.getName());
        dto.setBrand(p.getBrand());
        dto.setBarcode(p.getBarcode());
        dto.setWarehouseName(stock.getWarehouse().getName());
        dto.setQuantity(stock.getQuantity());
        dto.setMinStock(stock.getMinStock());
        dto.setLowStock(lowStock);
        dto.setRetailPrice(p.getRetailPrice());
        dto.setWholesalePrice(p.getWholesalePrice());
        dto.setSku(p.getSku());
        dto.setUnit_id(p.getUnit().getId());

        return dto;
    }



    public InputProduct toEntity(InputProductCreateDto product, Unit unit) {

        Product p = new Product();
        p.setName(product.getName());
        p.setBrand(product.getBrand());
        p.setBarcode(product.getBarcode());
        p.setRetailPrice(product.getRetailPrice());
        p.setWholesalePrice(product.getWholesalePrice());
        p.setSku(product.getSku());
        p.setUnit(unit);

        InputProduct ip = new InputProduct();
        ip.setProduct(p);
        return ip;
    }

}

