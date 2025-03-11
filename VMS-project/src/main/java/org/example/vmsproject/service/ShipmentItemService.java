package org.example.vmsproject.service;

import org.example.vmsproject.dto.request.ShipmentItemRequest;
import org.example.vmsproject.entity.ShipmentItem;

public interface ShipmentItemService {
    ShipmentItem create(ShipmentItemRequest request);
    ShipmentItem update(Long id, ShipmentItemRequest request);
    void delete(Long id);
}
