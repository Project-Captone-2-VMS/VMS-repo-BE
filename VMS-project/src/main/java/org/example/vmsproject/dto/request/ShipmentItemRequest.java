package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItemRequest {
    private String shipmentItemName;
    private double price;
    private int quantity;
    private Boolean  status;
    private Long warehouseId;
    private Long routeId;
}

