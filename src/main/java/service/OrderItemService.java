package service;

import dto.order.OrderDto;
import dto.orderItem.OrderItemDto;

import java.sql.SQLException;
import java.util.Collection;

public interface OrderItemService {
    // DAO(Domain Model(entity) -> DTO
    OrderItemDto getOrderItem(long id) throws SQLException;
    Collection<OrderItemDto> getOrderItems() throws SQLException;
    Collection<OrderItemDto> getOrderItemsByProduct(long product_id) throws SQLException;

    void saveOrderItem(OrderItemDto orderItem) throws SQLException;
    void updateOrderItem(OrderItemDto orderItem) throws SQLException;
    void deleteOrderItem(long id) throws SQLException;

}
