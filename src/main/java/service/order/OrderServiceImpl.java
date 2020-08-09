package service.order;

import domain.entity.customer.CustomerEntity;
import domain.entity.order.OrderEntity;
import domain.entity.orderItem.OrderItemEntity;
import dto.customer.CustomerDto;
import dto.order.OrderDto;
import dto.orderItem.OrderItemDto;
import exception.exceptions.nullExceptions.OrderNullException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CustomerRepository;
import repository.OrderItemRepository;
import repository.OrderRepository;
import service.OrderService;
import util.Converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //advanced
    @Transactional
    @Override
    public void purchaseOrder(OrderDto order) {

    }
    @Override
    public Collection<OrderItemDto> getOrderItems(long id){
        Collection<OrderItemEntity> orderItemEntities = orderRepository.findOne(id).getItems();
        if (orderItemEntities == null){
            throw new OrderNullException("getOrderItems for : " + id, "Service");
        }
        return Converter.collectionConvertEntityToDto(orderItemEntities, OrderItemDto.class);
    }
    @Override
    public CustomerDto getMyCustomer(long id) {
        CustomerEntity customerEntity = orderRepository.findOne(id).getCustomer();
        if (customerEntity == null){
            throw new OrderNullException("getMyCustomer for : " + id, "Service");
        }
        return Converter.EntityToDto(customerEntity);
    }

    //Basic

    // Create
    @Override
    public void saveOrder(OrderDto order) {
        OrderEntity orderEntity = Converter.DtoToEntity(order);
        orderRepository.save(orderEntity);
    }

    // Read
    @Override
    public OrderDto getOrderById(long id) {
        OrderEntity orderEntity = orderRepository.findOne(id);
        if (orderEntity == null){
            throw new OrderNullException("getOrderById: " + id, "Service");
        }
        return Converter.EntityToDto(orderEntity);
    }

    @Override
    public Collection<OrderDto> getAllOrders() {
        Collection<OrderEntity> orderEntities = orderRepository.findAll();
        if (orderEntities == null){
            throw new OrderNullException("getAllOrders: ", "Service");
        }
        return Converter.collectionConvertEntityToDto(orderEntities, OrderDto.class);
    }
    // update
    @Override
    public void updateOrder(OrderDto order) {
        OrderEntity orderEntity = Converter.DtoToEntity(order);
        orderRepository.update(orderEntity);
    }
    // delete
    @Transactional
    @Override
    public void deleteOrder(long id) {
        if (getOrderById(id) != null){
            orderRepository.delete(id);
        }
    }
}
