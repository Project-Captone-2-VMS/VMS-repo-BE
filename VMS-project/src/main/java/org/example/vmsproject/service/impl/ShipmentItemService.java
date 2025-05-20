package org.example.vmsproject.service.impl;

import jakarta.transaction.Transactional;
import org.example.vmsproject.dto.ShipmentItemDTO;
import org.example.vmsproject.dto.request.ShipmentItemRequest;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.ShipmentItem;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.ProductRepository;
import org.example.vmsproject.repository.RouteRepository;
import org.example.vmsproject.repository.ShipmentItemRepository;
import org.example.vmsproject.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipmentItemService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShipmentItemRepository shipmentItemRepository;


    @Transactional
    public ShipmentItem saveShipmentItem(ShipmentItemRequest request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_FOUND));

        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new AppException(ErrorCode.ROUTE_NOT_FOUND));

        Product product = productRepository.findByProductNameAndWarehouse(
                request.getShipmentItemName(), warehouse
        ).orElseThrow(() -> new AppException(ErrorCode.INVALID_PRODUCT));

        int newQuantity = product.getQuantity() - request.getQuantity();
        if (newQuantity < 0) {
            throw new AppException(ErrorCode.INVALID_CAPACITY);
        }

        product.setQuantity(newQuantity);
        productRepository.save(product);
        boolean status = request.getStatus() != null && request.getStatus();


        ShipmentItem item = ShipmentItem.builder()
                .shipmentItemName(request.getShipmentItemName())
                .price(product.getPrice())
                .quantity(request.getQuantity())
                .status(status)
                .warehouse(warehouse)
                .route(route)
                .build();

        return shipmentItemRepository.save(item);
    }

    @Transactional
    public void deleteShipmentItem(Long id) {
        ShipmentItem item = shipmentItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));

        Optional<Product> productOpt = productRepository.findByProductNameAndWarehouse(
                item.getShipmentItemName(), item.getWarehouse()
        );

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);
        } else {
             throw new AppException(ErrorCode.INVALID_PRODUCT);
        }

        shipmentItemRepository.deleteById(id);
    }

    @Transactional
    public ShipmentItem updateShipmentItem(Long id, ShipmentItemRequest request) {
        ShipmentItem existingItem = shipmentItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_FOUND));

        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new AppException(ErrorCode.ROUTE_NOT_FOUND));

        Product product = productRepository.findByProductNameAndWarehouse(
                request.getShipmentItemName(), warehouse
        ).orElseThrow(() -> new AppException(ErrorCode.INVALID_PRODUCT));

        int quantityDifference = request.getQuantity() - existingItem.getQuantity();
        int newQuantity = product.getQuantity() - quantityDifference;

        if (newQuantity < 0) {
            throw new AppException(ErrorCode.INVALID_CAPACITY);
        }

        product.setQuantity(newQuantity);
        productRepository.save(product);

        boolean status = request.getStatus() != null && request.getStatus();

        existingItem.setShipmentItemName(request.getShipmentItemName());
        existingItem.setPrice(product.getPrice());
        existingItem.setQuantity(request.getQuantity());
        existingItem.setStatus(status);
        existingItem.setWarehouse(warehouse);
        existingItem.setRoute(route);

        return shipmentItemRepository.save(existingItem);
    }

    public List<ShipmentItemDTO> getAllShipmentItems() {
        return shipmentItemRepository.findAll()
                .stream()
                .map(item -> new ShipmentItemDTO(
                        item.getShipmentItemId(),
                        item.getShipmentItemName(),
                        item.getPrice(),
                        item.getQuantity(),
                        item.getStatus(),
                        item.getWarehouse().getWarehouseId(),
                        item.getRoute().getRouteId()
                ))
                .collect(Collectors.toList());
    }


}
