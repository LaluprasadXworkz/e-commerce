package com.netzwerk.ecommerce.service;

import com.netzwerk.ecommerce.dto.InventoryDto;
import com.netzwerk.ecommerce.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImplementation {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryDto saveDTO(InventoryDto dto){
        return inventoryRepository.save(dto);
    }

    public InventoryDto findById(Integer id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public List<InventoryDto> findAll() {
        return inventoryRepository.findAll();
    }
}
