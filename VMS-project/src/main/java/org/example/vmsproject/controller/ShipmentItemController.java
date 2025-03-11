package org.example.vmsproject.controller;

import org.example.vmsproject.dto.request.ShipmentItemRequest;
import org.example.vmsproject.entity.ShipmentItem;
import org.example.vmsproject.service.ShipmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment-item/")
@CrossOrigin(origins = "*")
public class ShipmentItemController {
    @Autowired
    private ShipmentItemService shipmentItemService;

//    @GetMapping("getAll/{id}")
//    public ResponseEntity<List<ShipmentItem>>getAll(@PathVariable("id") Long id){
//        List<ShipmentItem> itemList = itemService.getAllByShipmentId(id);
//        return ResponseEntity.ok(itemList);
//    }
    @PostMapping("save")
    public ResponseEntity<?>save(@RequestBody ShipmentItemRequest request){
        ShipmentItem item = shipmentItemService.create(request);
        return ResponseEntity.ok(item);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<?>update(@PathVariable("id") Long id, @RequestBody ShipmentItemRequest request){
        ShipmentItem item = shipmentItemService.update(id, request);
        return ResponseEntity.ok(item);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") Long id){
        shipmentItemService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
