package com.netzwerk.ecommerce.repository;

import com.netzwerk.ecommerce.dto.InventoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryDto, Integer> {
    @Query("SELECT DISTINCT i.make FROM InventoryDto i")
    List<String> findDistinctMakes();
    @Query("SELECT DISTINCT i.model FROM InventoryDto i WHERE i.make = :make")
    List<String> findDistinctModelsByMake(@Param("make") String make);
    @Query("SELECT DISTINCT i.productCode FROM InventoryDto i WHERE i.make = :make AND i.model = :model")
    List<String> findDistinctProductCodesByMakeAndModel(@Param("make") String make, @Param("model") String model);
    @Query("SELECT i FROM InventoryDto i WHERE i.make = :make AND i.model = :model AND i.productCode = :productCode")
    InventoryDto findByMakeModelAndProductCode(@Param("make") String make, @Param("model") String model, @Param("productCode") String productCode);
}
