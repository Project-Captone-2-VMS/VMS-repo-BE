package org.example.vmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Warehouse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItemDTO {
    private Long shipmentItemId;
    private String shipmentItemName;
    private double price;
    private int quantity;
    private Boolean status;
    private Warehouse warehouse;
    private Route route;


}

