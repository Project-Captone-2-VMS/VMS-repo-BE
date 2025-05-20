package org.example.vmsproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.vmsproject.dto.request.ShipmentItemRequest;
import org.example.vmsproject.entity.ShipmentItem;
import org.example.vmsproject.service.impl.ShipmentItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment-items")
@RequiredArgsConstructor
public class ShipmentItemController {

    private final ShipmentItemService shipmentItemService;

    @PostMapping("/save")
    public ResponseEntity<ShipmentItem> saveShipmentItem(@RequestBody ShipmentItemRequest request) {
        ShipmentItem savedItem = shipmentItemService.saveShipmentItem(request);
        return ResponseEntity.ok(savedItem);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ShipmentItem> updateShipmentItem(
            @PathVariable Long id,
            @RequestBody ShipmentItemRequest request) {
        ShipmentItem updatedItem = shipmentItemService.updateShipmentItem(id, request);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShipmentItem(@PathVariable Long id) {
        shipmentItemService.deleteShipmentItem(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<ShipmentItem>> getAllShipmentItems() {
        List<ShipmentItem> items = shipmentItemService.getAllShipmentItems();
        return ResponseEntity.ok(items);
    }


}

