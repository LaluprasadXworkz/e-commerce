package com.netzwerk.ecommerce.dto;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Component

public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Created_By")
    @NonNull
    private String createdBy;

    @Column(name = "Created_On")
    @NonNull
    private LocalDateTime createdOn;

    @NonNull
    @OneToOne(mappedBy = "audit")
    private OrderDto dto;
}
