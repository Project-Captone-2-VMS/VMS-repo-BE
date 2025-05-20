package org.example.vmsproject.repository;

import org.example.vmsproject.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {
    List<ShipmentItem> findAllByRouteRouteId(Long routeId);
}

