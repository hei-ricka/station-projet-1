package com.ricka.princy.stationprojet1.endpoint.rest.mapper;

import com.ricka.princy.stationprojet1.endpoint.rest.model.CrupdateProduct;
import com.ricka.princy.stationprojet1.model.Product;
import com.ricka.princy.stationprojet1.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {
    private final StationService stationService;

    public Product toRest(CrupdateProduct crupdateProduct){
        return Product
                .builder()
                .id(crupdateProduct.getId())
                .name(crupdateProduct.getName())
                .stock(crupdateProduct.getStock())
                .createdAt(crupdateProduct.getCreatedAt())
                .unitPrice(crupdateProduct.getUnitPrice())
                .updatedAt(crupdateProduct.getUpdatedAt())
                .station(stationService.getById(crupdateProduct.getStationId()))
                .build();
    }
}
