package service.customer;

import domain.entity.customer.CustomerEntity;
import domain.entity.order.OrderEntity;
import dto.customer.CustomerDto;
import dto.order.OrderDto;
import exception.exceptions.nullExceptions.CustomerNullException;
import exception.exceptions.nullExceptions.OrderNullException;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CustomerRepository;
import service.CustomerService;
import util.Converter;

import java.sql.SQLException;
import java.util.Collection;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto getCustomer(long id) throws SQLException {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        if(customerEntity == null){
            throw new CustomerNullException("getCustomer" + id, "Service");
        }
        return Converter.EntityToDto(customerEntity);
    }
    @Override
    public Collection<CustomerDto> getCustomersByName(String name) throws SQLException {
        Collection<CustomerEntity> customerEntities = customerRepository.findByName(name);
        if(customerEntities == null){
            throw new CustomerNullException("getCustomersByName: " + name, "Service");
        }
        return Converter.collectionConvertEntityToDto(customerEntities, CustomerDto.class);
    }
    @Override
    public Collection<CustomerDto> getCustomersByAddress(String address) throws SQLException {
        Collection<CustomerEntity> customerEntities = customerRepository.findByAddress(address);
        if(customerEntities == null){
            throw new CustomerNullException("getCustomersByAddress: " + address, "Service");
        }
        return Converter.collectionConvertEntityToDto(customerEntities, CustomerDto.class);
    }

    @Override
    public Collection<OrderDto> getOrders(long id) throws SQLException {
        Collection<OrderEntity> orderEntities = customerRepository.findOne(id).getOrders();
        if(orderEntities == null){
            throw new CustomerNullException("getOrders: " + id, "Service");
        }
        return Converter.collectionConvertEntityToDto(orderEntities, OrderDto.class);
    }

    @Override
    public Collection<CustomerDto> getCustomers() throws SQLException {
        Collection<CustomerEntity> customerEntities = customerRepository.findAll();
        if(customerEntities == null){
            throw new CustomerNullException("getCustomers: ", "Service");
        }
        return Converter.collectionConvertEntityToDto(customerEntities, CustomerDto.class);
    }

    @Override
    public Collection<CustomerDto> getCustomersByPage(int index, int size) {
        return null;
    }

    @Override
    public void saveCustomer(CustomerDto customer) throws SQLException {
        CustomerEntity customerEntity = Converter.DtoToEntity(customer);
        customerRepository.save(customerEntity);
    }

    @Override
    public void updateCustomer(CustomerDto customer) throws SQLException {
        CustomerEntity customerEntity = Converter.DtoToEntity(customer);
        customerRepository.update(customerEntity);
    }

    @Transactional
    @Override
    public void deleteCustomer(long id) throws SQLException {
        if (getCustomer(id) != null) {
            customerRepository.delete(id);
        }
    }

}
