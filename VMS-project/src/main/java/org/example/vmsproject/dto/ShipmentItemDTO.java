package org.example.vmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItemDTO {
    private Long shipmentItemId;
    private String shipmentItemName;
    private double price;
    private int quantity;
    private Boolean status;
    private Long warehouseId;
    private Long routeId;


}

