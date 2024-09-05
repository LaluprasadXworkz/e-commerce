package com.netzwerk.ecommerce.dto;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "customer_details")
public class CustomerDetailsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private Integer customerId;
    @Column(name = "customerName")
    private String customerName;
    @Column(name = "customerEmailId")
    private String customerEmailId;
    @Column(name = "contactNumber")
    private String contactNumber;
    @Column(name = "gstNumber")
    private String gstNumber;
    @Column(name = "mailingAddress")
    private String mailingAddress;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "pinCode")
    private Integer pinCode;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "address3")
    private String address3;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    private String createdOn;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_on")
    private String updatedOn;

}
