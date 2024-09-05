package com.netzwerk.ecommerce.service.customer;

import com.netzwerk.ecommerce.dto.CustomerDetailsDto;
import com.netzwerk.ecommerce.repository.CustomerDetailsRepository;
import com.netzwerk.ecommerce.util.CustomerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImplementation {

    private final CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerDetailsRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    public String onSave(CustomerDetailsDto customerDetailsDto) {
        if (customerDetailsDto != null) {
            CustomerUtil.assignValuesToCustomerDetails(customerDetailsDto);
            log.info("after assignment {}", customerDetailsDto);
            customerDetailsRepository.save(customerDetailsDto);
            return "Customer details saved";
        }
        return "Customer details not saved";
    }

    public String checkCustomerName(String customerName) {
        log.info("checking duplicate for customerName: {}", customerName);

        List<String> check = customerDetailsRepository.checkCustomerName();
        boolean customerExists = check.stream().filter(Objects::nonNull).anyMatch(str -> str.equals(customerName));
        return customerExists ? "customer exists" : " ";
    }


    public String checkCustomerEmail(String customerEmail) {
        log.info("checking duplicate for customerEmail: {}", customerEmail);
        return Optional.ofNullable(customerDetailsRepository.checkCustomerEmail(customerEmail))
                .map(emailCheck -> "customer Email exists").orElse(" ");
    }

    public String checkCustomerContactNumber(String contactNumber) {
        log.info("checking duplicate for contactNumber:{}", contactNumber);
        if (customerDetailsRepository.checkCustomerContactNumber(contactNumber)) {
            return "customer contact number exists";
        }
        return "customer contact number not exists";
    }

    public String checkCustomerGstNumber(String gstNumber) {
        log.info("checking duplicate for gstNumber:{}", gstNumber);
        if (customerDetailsRepository.checkCustomerGstNumber(gstNumber)) {
            return "customer gstNumber exists";
        }
        return "customer gstNumber not exists";
    }

    public List<CustomerDetailsDto> getAllCustomers() {
        return customerDetailsRepository.findAll();
    }
}
