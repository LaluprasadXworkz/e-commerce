package com.netzwerk.ecommerce.controller;

import com.netzwerk.ecommerce.dto.CustomerDetailsDto;
import com.netzwerk.ecommerce.service.customer.CustomerServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/customer")
@Controller
@Slf4j
public class CustomerController {

    private final CustomerServiceImplementation customerServiceImplementation;

    @Autowired
    public  CustomerController(CustomerServiceImplementation customerServiceImplementation){
        this.customerServiceImplementation=customerServiceImplementation;
    }
    @GetMapping("/getCustomerDetails")
    public String homeToCustomer(Model model) {
        model.addAttribute("customer", new CustomerDetailsDto());
        return "customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomerDetails(@ModelAttribute CustomerDetailsDto customerDetailsDto) {
        log.info("Saving the customer details: {}", customerDetailsDto);
        customerServiceImplementation.onSave(customerDetailsDto);
        return "redirect:/customer/toView";
    }
    @GetMapping("/toView")
    public String showCustomerList(Model model) {
        log.info("Fetching customer list");
        List<CustomerDetailsDto> customerList = customerServiceImplementation.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "customerList";
    }
}
