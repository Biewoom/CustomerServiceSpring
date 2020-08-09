package service;

import dto.customer.CustomerDto;
import dto.order.OrderDto;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    CustomerDto getCustomer(long id) throws SQLException;
    Collection<CustomerDto> getCustomers() throws SQLException;
    Collection<CustomerDto> getCustomersByName(String name) throws SQLException;
    Collection<CustomerDto> getCustomersByAddress(String address) throws SQLException;
    Collection<OrderDto> getOrders(long id) throws SQLException;

    Collection<CustomerDto> getCustomersByPage(int index, int size);

    void saveCustomer(CustomerDto customer) throws SQLException;
    void updateCustomer(CustomerDto customer) throws SQLException;
    void deleteCustomer(long id) throws SQLException;

}
