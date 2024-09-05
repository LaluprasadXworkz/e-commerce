package com.netzwerk.ecommerce.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "inventory")
public class InventoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "make")
    private String make;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "rate_per_item")
    private Double ratePerItem;

    private String hsn;

    @Column(name = "stock_in_hand")
    private Integer stockInHand;
    private Integer sgst;
    private Integer cgst;
    private Integer igst;
    private Integer utgst;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private String createdOn;
}
