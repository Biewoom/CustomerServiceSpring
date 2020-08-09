package repository;

import domain.entity.order.OrderEntity;
import util.Pageable;

import java.util.Collection;
import java.util.List;

public interface OrderRepository {
    OrderEntity findOne(long id);
    Collection<OrderEntity> findAll();
    Collection<OrderEntity> findAll(Pageable page);

    void save(OrderEntity order);
    void update(OrderEntity order);
    void delete(long id);

}
