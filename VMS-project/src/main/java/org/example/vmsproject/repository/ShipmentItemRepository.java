package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {
    Optional<ShipmentItem> findByShipmentItemNameAndRoute(String shipmentItemName, Route route);
}
