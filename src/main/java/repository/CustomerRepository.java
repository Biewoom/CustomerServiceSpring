package repository;

import domain.entity.customer.CustomerEntity;
import domain.entity.order.OrderEntity;
import util.Pageable;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CustomerRepository {
    CustomerEntity findOne(long id) throws SQLException;
    Collection<CustomerEntity> findAll() throws SQLException;
    Collection<CustomerEntity> findAll(Pageable page) throws SQLException;
    Collection<CustomerEntity> findByAddress(String address) throws SQLException;

    Collection<CustomerEntity> findByName(String name) throws SQLException;
    void save(CustomerEntity customer) throws SQLException;
    void delete(long id) throws SQLException;
    void update(CustomerEntity customer) throws SQLException;

}
