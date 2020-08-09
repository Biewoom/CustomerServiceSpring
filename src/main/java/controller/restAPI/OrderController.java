package controller.restAPI;

import dto.customer.CustomerDto;
import dto.order.OrderDto;
import dto.orderItem.OrderItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

import java.util.Collection;

@RestController
@RequestMapping("/apis/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    public OrderDto getOrderById(@PathVariable("id") long id){
        return orderService.getOrderById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public Collection<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    // <TO-DO> Read its own orderItemDto
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{id}/orderItems", produces = "application/json")
    public Collection<OrderItemDto> getOrderItemsById(@PathVariable("id") long id){
        Collection<OrderItemDto> orderItems = orderService.getOrderItems(id);
        return orderItems;
    }
    // <TO-DO> Read its own customer
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{id}/customer", produces= "application/json")
    public CustomerDto getCustomerById(@PathVariable("id") long id){
        CustomerDto customerDto = orderService.getMyCustomer(id);
        return customerDto;
    }
    // <TO-DO> Read Collection of OrderDto by Date
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void save(@RequestBody OrderDto order){
        orderService.saveOrder(order);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody OrderDto order){
        orderService.updateOrder(order);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id){
        orderService.deleteOrder(id);
    }
}
