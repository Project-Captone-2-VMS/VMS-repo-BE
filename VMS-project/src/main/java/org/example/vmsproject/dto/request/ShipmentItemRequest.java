package org.example.vmsproject.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Warehouse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItemRequest {
    private Long shipmentItemId;
    private String shipmentItemName;
    private double price;
    private int quantity;
    private boolean status;
    private Warehouse warehouse;
    private Route route;
    private Long routeId;
    private Long warehouseId;
}
