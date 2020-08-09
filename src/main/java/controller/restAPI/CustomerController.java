package controller.restAPI;

import dto.customer.CustomerDto;
import dto.order.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

import java.sql.SQLException;
import java.util.Collection;

@RestController
@RequestMapping("/apis/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/name", produces = "application/json")
    public Collection<CustomerDto> getCustomerByName(@RequestParam("name") String name) throws SQLException {
        return customerService.getCustomersByName(name);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value ="/address", produces = "application/json")
    public Collection<CustomerDto> getCustomerByAddress(@RequestParam("address") String address) throws SQLException{
        return customerService.getCustomersByAddress(address);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    public CustomerDto getCustomerById(@PathVariable("id") long id) throws SQLException{
        return customerService.getCustomer(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public Collection<CustomerDto> getCustomers() throws SQLException {
        return customerService.getCustomers();
    }
    // <TO-DO> get Collection of My own orders
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/orders", produces = "application/json")
    public Collection<OrderDto> getMyOrders(@PathVariable("id") long id) throws SQLException{
        return customerService.getOrders(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void create(@RequestBody CustomerDto customer) throws SQLException {
        customerService.saveCustomer(customer);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody CustomerDto customer) throws SQLException {
        customerService.updateCustomer(customer);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") long id) throws SQLException {
        customerService.deleteCustomer(id);
    }
}
