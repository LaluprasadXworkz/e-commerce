package com.netzwerk.ecommerce.repository;

import com.netzwerk.ecommerce.dto.CustomerDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetailsDto, Integer> {

    @Query("SELECT c.customerName FROM CustomerDetailsDto c")
    List<String> checkCustomerName();

    @Query("SELECT c FROM CustomerDetailsDto c WHERE c.customerEmailId = :customerEmailId")
    CustomerDetailsDto checkCustomerEmail(@Param("customerEmailId") String customerEmailId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CustomerDetailsDto c WHERE c.contactNumber = :contactNumber")
    boolean checkCustomerContactNumber(@Param("contactNumber") String contactNumber);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CustomerDetailsDto c WHERE c.gstNumber = :gstNumber")
    boolean checkCustomerGstNumber(@Param("gstNumber") String gstNumber);

}

