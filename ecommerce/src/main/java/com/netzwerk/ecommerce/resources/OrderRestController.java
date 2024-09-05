package com.netzwerk.ecommerce.resources;

import com.netzwerk.ecommerce.dto.InventoryDto;
import com.netzwerk.ecommerce.service.OrderServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    private final OrderServiceImplementation orderServiceImplementation;
    @Autowired
    public OrderRestController(OrderServiceImplementation orderServiceImplementation){
        this.orderServiceImplementation=orderServiceImplementation;
    }

    @GetMapping("/getModel/{make}")
    public List<String> getModel(@PathVariable("make") String make) {
        log.info("Received request to get models for make: {}", make);
        List<String> models = orderServiceImplementation.getDistinctModel(make);
        log.info("Returning models for make '{}': {}", make, models);
        return models;
    }

    @GetMapping("/getProductCode/{make}/{model}")
    public List<String> getProductCode(@PathVariable("make") String make, @PathVariable("model") String model) {
        log.info("Received request to get product codes for make: '{}' and model: '{}'", make, model);
        List<String> productCodes = orderServiceImplementation.getDistinctProductCode(make, model);
        log.info("Returning product codes for make '{}' and model '{}': {}", make, model, productCodes);
        return productCodes;
    }

    @GetMapping("/getPriceAndStock/{make}/{model}/{productCode}")
    public InventoryDto getPriceAndStock(@PathVariable("make") String make,
                                         @PathVariable("model") String model,
                                         @PathVariable("productCode") String productCode) {
        log.info("Received request to get price and stock for make: '{}', model: '{}', productCode: '{}'", make, model, productCode);
        InventoryDto inventory = orderServiceImplementation.getPriceAndStockInHand(make, model, productCode);
        log.info("Returning price and stock information for make: '{}', model: '{}', productCode: '{}': {}", make, model, productCode, inventory);
        return inventory;
    }
}
