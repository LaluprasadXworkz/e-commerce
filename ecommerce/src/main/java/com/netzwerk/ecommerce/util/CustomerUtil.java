package com.netzwerk.ecommerce.util;

import com.netzwerk.ecommerce.constant.ServiceConstant;
import com.netzwerk.ecommerce.dto.CustomerDetailsDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
public class CustomerUtil {
    private CustomerUtil() {

    }
    public static void assignValuesToCustomerDetails(CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setCreatedBy(ServiceConstant.NA.toString());
        customerDetailsDto.setCreatedOn(LocalDateTime.now().toString());
        customerDetailsDto.setUpdatedBy(ServiceConstant.NA.toString());
        customerDetailsDto.setUpdatedOn(ServiceConstant.NA.toString());
        if(customerDetailsDto.getAddress2()==null){
            customerDetailsDto.setAddress2(ServiceConstant.NA.toString());
        }
        if(customerDetailsDto.getAddress3()==null){
            customerDetailsDto.setAddress3(ServiceConstant.NA.toString());
        }
    }
}
