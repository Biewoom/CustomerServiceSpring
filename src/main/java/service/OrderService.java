package service;

import dto.customer.CustomerDto;
import dto.order.OrderDto;
import dto.orderItem.OrderItemDto;

import java.util.Collection;

public interface OrderService {
    // advanced
    void purchaseOrder(OrderDto order);
    Collection<OrderItemDto> getOrderItems(long id);
    CustomerDto getMyCustomer(long id);

    // Basic
//  Create
    void saveOrder(OrderDto order);
//  Read
    OrderDto getOrderById(long id);
    Collection<OrderDto> getAllOrders();
    //  Update
    void updateOrder(OrderDto order);
//  Delete
    void deleteOrder(long id);

}
