package org.example.vmsproject.service.impl;

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
import org.example.vmsproject.service.ShipmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmenItemServiceImpl implements ShipmentItemService {
    @Autowired
    private ShipmentItemRepository shipmentItemRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public ShipmentItem create(ShipmentItemRequest request) {
        Product product = productRepository.findByProductNameAndWarehouse(
                        request.getShipmentItemName(), request.getWarehouse())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PRODUCT));

        Warehouse warehouse = warehouseRepository.findWarehouseByProducts(product);

        int requiredQuantity = request.getQuantity();
        if (product.getQuantity() < requiredQuantity || warehouse.getCurrentStock() < requiredQuantity) {
            throw new AppException(ErrorCode.INVALID_CAPACITY);
        }
        product.setQuantity(product.getQuantity() - requiredQuantity);
        warehouse.setCurrentStock(warehouse.getCurrentStock() - requiredQuantity);

        productRepository.save(product);
        warehouseRepository.save(warehouse);

        Optional<ShipmentItem> existingItem = shipmentItemRepository.findByShipmentItemNameAndRoute(
                request.getShipmentItemName(), request.getRoute());
        if (existingItem.isPresent()) {
            ShipmentItem shipmentItem = existingItem.get();
            shipmentItem.setQuantity(shipmentItem.getQuantity() + requiredQuantity);
            return shipmentItemRepository.save(shipmentItem);
        }

        ShipmentItem newShipmentItem = ShipmentItem.builder()
                .shipmentItemName(request.getShipmentItemName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .warehouse(request.getWarehouse())
                .route(request.getRoute())
                .build();

        return shipmentItemRepository.save(newShipmentItem);
    }

    @Override
    public ShipmentItem update(Long id, ShipmentItemRequest request) {
        ShipmentItem shipmentItem = shipmentItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_ITEM_NOT_FOUND));

        if (Boolean.TRUE.equals(shipmentItem.isStatus())) {
            throw new AppException(ErrorCode.CANNOT_UPDATE_SHIPMENT);
        }

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOT_FOUND));

        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new AppException(ErrorCode.ROUTE_NOT_FOUND));

        Product product = productRepository.findByProductNameAndWarehouse(
                        shipmentItem.getShipmentItemName(), warehouse)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PRODUCT));

        int deltaQuantity = request.getQuantity() - shipmentItem.getQuantity();

        if (deltaQuantity > 0) {
            if (product.getQuantity() < deltaQuantity || warehouse.getCurrentStock() < deltaQuantity) {
                throw new AppException(ErrorCode.INVALID_CAPACITY);
            }
            product.setQuantity(product.getQuantity() - deltaQuantity);
            warehouse.setCurrentStock(warehouse.getCurrentStock() - deltaQuantity);
        } else {
            product.setQuantity(product.getQuantity() - deltaQuantity);
            warehouse.setCurrentStock(warehouse.getCurrentStock() - deltaQuantity);
        }

        shipmentItem.setShipmentItemName(request.getShipmentItemName());
        shipmentItem.setPrice(request.getPrice());
        shipmentItem.setQuantity(request.getQuantity());
        shipmentItem.setWarehouse(warehouse);
        shipmentItem.setRoute(route);

        productRepository.save(product);
        warehouseRepository.save(warehouse);
        return shipmentItemRepository.save(shipmentItem);
    }


    @Override
    public void delete(Long id) {
        ShipmentItem shipmentItem = shipmentItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SHIPMENT_ITEM_NOT_FOUND));

        if (shipmentItem.isStatus()) {
            throw new AppException(ErrorCode.CANNOT_DELETE_SHIPMENT);
        }

        Product product = productRepository.findByProductNameAndWarehouse(
                        shipmentItem.getShipmentItemName(), shipmentItem.getWarehouse())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PRODUCT));

        Warehouse warehouse = warehouseRepository.findWarehouseByProducts(product);

        product.setQuantity(product.getQuantity() + shipmentItem.getQuantity());
        warehouse.setCurrentStock(warehouse.getCurrentStock() + shipmentItem.getQuantity());
        productRepository.save(product);
        warehouseRepository.save(warehouse);

        shipmentItemRepository.delete(shipmentItem);
    }

}
