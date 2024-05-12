package com.ricka.princy.stationprojet1.endpoint.rest.mapper;

import com.ricka.princy.stationprojet1.endpoint.rest.model.CrupdateProduct;
import com.ricka.princy.stationprojet1.entity.Product;
import com.ricka.princy.stationprojet1.service.ProductTemplateService;
import com.ricka.princy.stationprojet1.service.StationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {
    private final StationService stationService;
    private final ProductTemplateService productTemplateService;

    public Product toRest(CrupdateProduct crupdateProduct){
        return Product
                .builder()
                .id(crupdateProduct.getId())
                .evaporation(crupdateProduct.getEvaporation())
                .createdAt(crupdateProduct.getCreatedAt())
                .unitPrice(crupdateProduct.getUnitPrice())
                .updatedAt(crupdateProduct.getUpdatedAt())
                .station(stationService.getById(crupdateProduct.getStationId()))
                .productTemplate(productTemplateService.getById(crupdateProduct.getProductTemplateId()))
                .build();
    }
}
