package com.netzwerk.ecommerce.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ToString
@Table(name = "inventoryInfo")
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

    @Column(name = "gst_type")
    private String gstType;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private String createdOn;
}
