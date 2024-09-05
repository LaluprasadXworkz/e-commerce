package com.netzwerk.ecommerce.controller;

import com.netzwerk.ecommerce.dto.InventoryDto;
import com.netzwerk.ecommerce.service.InventoryServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImplementation inventoryServiceImplementation;

    @PostMapping("/save")
    public ResponseEntity<InventoryDto> saveDevice(@RequestBody InventoryDto dto){
        InventoryDto save = inventoryServiceImplementation.saveDTO(dto);
        if (save!=null){
            System.out.println(dto.toString());
        }else {
            System.out.println("DTO NOT SAVED !");
        }
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> findById(@PathVariable Integer id) {
        InventoryDto dto = inventoryServiceImplementation.findById(id);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryDto>> findAll() {
        List<InventoryDto> allItems = inventoryServiceImplementation.findAll();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

}
