package com.netzwerk.ecommerce.repository;

import com.netzwerk.ecommerce.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDto,Integer> {
//    Page<OrderDto> findAll(Pageable pageable);
}
