package com.netzwerk.ecommerce.service;

import com.netzwerk.ecommerce.dto.*;
import com.netzwerk.ecommerce.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImplementation {

    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImplementation(InventoryRepository inventoryRepository, OrderRepository orderRepository) {
        this.inventoryRepository = inventoryRepository;
        this.orderRepository = orderRepository;
    }

    public List<String> getDistinctMakes() {
        List<String> makes = inventoryRepository.findDistinctMakes();
        log.info("Found {} distinct makes.", makes.size());
        return makes;
    }

    public List<String> getDistinctModel(String make) {
        log.info("Invoking getDistinctModel method for make: {}", make);
        List<String> models = inventoryRepository.findDistinctModelsByMake(make);
        log.info("Found {} distinct models for make: {}", models.size(), make);
        return models;
    }

    public List<String> getDistinctProductCode(String make, String model) {
        log.info("Invoking getDistinctProductCode method for make: {}, model: {}", make, model);
        List<String> productCodes = inventoryRepository.findDistinctProductCodesByMakeAndModel(make, model);
        log.info("Found {} distinct product codes for make: {}, model: {}", productCodes.size(), make, model);
        return productCodes;
    }

    public InventoryDto getPriceAndStockInHand(String make, String model, String pCode) {
        log.info("Invoking getPriceAndStockInHand method for make: {}, model: {}, product code: {}", make, model, pCode);
        InventoryDto inventoryDto = inventoryRepository.findByMakeModelAndProductCode(make, model, pCode);
        if (inventoryDto != null) {
            log.info("Found price: {}, stock in hand: {} for product code: {}", inventoryDto.getRatePerItem(), inventoryDto.getStockInHand(), pCode);
        } else {
            log.warn("No data found for make: {}, model: {}, product code: {}", make, model, pCode);
        }
        return inventoryDto;
    }

    public OrderDto saveOrder(OrderDto order) {
        if (order != null) {
            order.setCreatedBy("Lalu");
            order.setCreatedOn(LocalDateTime.now().toString());
            OrderDto savedOrder = orderRepository.save(order);
            log.info("Order saved successfully with ID: {}", savedOrder.getId());
            return savedOrder;
        } else {
            log.error("Failed to save order: Order object is null.");
            return null;
        }
    }
}
