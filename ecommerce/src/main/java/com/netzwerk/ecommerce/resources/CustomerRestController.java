package com.netzwerk.ecommerce.resources;

import com.netzwerk.ecommerce.service.customer.CustomerServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerRestController {

    private final CustomerServiceImplementation customerServiceImplementation;

    @Autowired
    public CustomerRestController(CustomerServiceImplementation customerServiceImplementation) {
        this.customerServiceImplementation = customerServiceImplementation;
    }

    @GetMapping("/checkCustomerName/{customerName}")
    public String checkCustomerName(@PathVariable String customerName){
        return customerServiceImplementation.checkCustomerName(customerName);
    }
    @GetMapping("/checkCustomerEmail/{customerEmail}")
    public String checkCustomerEmail(@PathVariable String customerEmail){
        return customerServiceImplementation.checkCustomerEmail(customerEmail);
    }
    @GetMapping("/checkCustomerContactNumber/{contactNumber}")
    public String checkCustomerContactNumber(@PathVariable String contactNumber){
        return customerServiceImplementation.checkCustomerContactNumber(contactNumber);
    }
    @GetMapping("/checkCustomerGstNumber/{customerGstNumber}")
    public String checkCustomerGstNumber(@PathVariable String customerGstNumber){
        return customerServiceImplementation.checkCustomerGstNumber(customerGstNumber);
    }
}
