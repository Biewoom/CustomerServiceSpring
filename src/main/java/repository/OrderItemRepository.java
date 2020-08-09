package repository;

import domain.entity.orderItem.OrderItemEntity;
import util.Pageable;

import java.sql.SQLException;
import java.util.Collection;

public interface OrderItemRepository {
    OrderItemEntity findOne(long id) throws SQLException;
    Collection<OrderItemEntity> findAll() throws SQLException;
    Collection<OrderItemEntity> findAll(Pageable page) throws SQLException;
    Collection<OrderItemEntity> findAllByProduct(long product_id);

    void save(OrderItemEntity orderItemEntity) throws SQLException;
    void delete(long id) throws SQLException;
    void update(OrderItemEntity orderItemEntity) throws SQLException;

}
