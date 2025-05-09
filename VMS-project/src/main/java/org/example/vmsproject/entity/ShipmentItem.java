package org.example.vmsproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "shipment_item")
public class ShipmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentItemId;
    private String shipmentItemName;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    @JsonBackReference(value = "warehouse-items")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    @JsonBackReference(value = "route-items")
    private Route route;


}
