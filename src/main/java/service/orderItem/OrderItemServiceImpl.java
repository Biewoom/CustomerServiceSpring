package service.orderItem;

import domain.entity.orderItem.OrderItemEntity;
import dto.order.OrderDto;
import dto.orderItem.OrderItemDto;
import exception.exceptions.nullExceptions.OrderItemNullException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderItemRepository;
import service.OrderItemService;
import util.Converter;

import java.sql.SQLException;
import java.util.Collection;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItemDto getOrderItem(long id) throws SQLException {
        OrderItemEntity orderItemEntity = orderItemRepository.findOne(id);
        if(orderItemEntity == null){
            throw new OrderItemNullException("getOrderItem: " + id, "Service");
        }
        return Converter.EntityToDto(orderItemEntity);
    }

    @Override
    public Collection<OrderItemDto> getOrderItems() throws SQLException {
        Collection<OrderItemEntity> orderItemEntities = orderItemRepository.findAll();
        if(orderItemEntities == null){
            throw new OrderItemNullException("getOrderItems", "Service");
        }
        return Converter.collectionConvertEntityToDto(orderItemEntities, OrderItemDto.class);
    }

    @Override
    public Collection<OrderItemDto> getOrderItemsByProduct(long product_id) throws SQLException {
        Collection<OrderItemEntity> orderItemEntities = orderItemRepository.findAllByProduct(product_id);
        if(orderItemEntities == null){
            throw new OrderItemNullException("getOrderItemsByProduct: " + product_id, "Service");
        }
        return Converter.collectionConvertEntityToDto(orderItemEntities, OrderItemDto.class);
    }

    @Override
    public void saveOrderItem(OrderItemDto orderItem) throws SQLException {
        OrderItemEntity orderItemEntity = Converter.DtoToEntity(orderItem);
        orderItemRepository.save(orderItemEntity);
    }

    @Override
    public void updateOrderItem(OrderItemDto orderItem) throws SQLException {
        OrderItemEntity orderItemEntity = Converter.DtoToEntity(orderItem);
        orderItemRepository.update(orderItemEntity);
    }

    @Transactional
    @Override
    public void deleteOrderItem(long id) throws SQLException {
        if (getOrderItem(id) != null){
            orderItemRepository.delete(id);
        }
    }
}
