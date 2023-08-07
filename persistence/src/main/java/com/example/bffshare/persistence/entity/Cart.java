package com.example.bffshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private User userId;
    private double sumPrice;
    @ElementCollection
    private Map<UUID,Integer> itemMap;
}
