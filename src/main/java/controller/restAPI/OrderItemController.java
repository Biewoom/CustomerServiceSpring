package controller.restAPI;

import dto.orderItem.OrderItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.OrderItemService;

import java.sql.SQLException;
import java.util.Collection;

@RestController
@RequestMapping("/apis/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{id}", produces = "application/json")
    public OrderItemDto getOrderItemById(@PathVariable("id") long id) throws SQLException {
        return orderItemService.getOrderItem(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public Collection<OrderItemDto> getOrderItems() throws SQLException {
     return orderItemService.getOrderItems();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="product/{product_id}", produces = "application/json")
    public Collection<OrderItemDto> getOrderItemsByProduct(@PathVariable("product_id") long product_id) throws SQLException{
        return orderItemService.getOrderItemsByProduct(product_id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void save(@RequestBody OrderItemDto orderItem) throws SQLException {
        orderItemService.saveOrderItem(orderItem);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody OrderItemDto orderItem) throws SQLException {
        orderItemService.updateOrderItem(orderItem);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id) throws SQLException {
        orderItemService.deleteOrderItem(id);
    }

}
