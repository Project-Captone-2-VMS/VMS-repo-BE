//package org.example.vmsproject.repository;
//
//import org.example.vmsproject.entity.ShipmentItem;
//import org.example.vmsproject.entity.Shipment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface ItemRepository extends JpaRepository<ShipmentItem, Long> {
//    List<ShipmentItem>findAllByWarehouseWarehouseId(Long id);
//    @Query("SELECT r FROM ShipmentItem r WHERE r.shipment.shipmentId = :shipment_id")
//    List<ShipmentItem> findAllByShipmentId(@Param("shipment_id")Long shipment_id);
//
//    Optional<ShipmentItem> findByItemNameAndShipment(String itemName, Shipment shipment);
//
////    @Query("SELECT i FROM ShipmentItem i WHERE i.shipment.shipmentId = :shipment_id AND i.itemName = :item_name")
////    <ShipmentItem> findByShipmentIdAndItemName(@Param("shipment_id") Long shipment_id, @Param("item_name") String item_name);
//
//}
