package dto.order;

import dto.Dto;
import dto.orderItem.OrderItemDto;
import dto.customer.CustomerDto;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class OrderDto extends Dto {
    // fields
    private long id;
    private Date orderDate;
    private Collection<OrderItemDto> items;
    private CustomerDto customer;

    // methods
    public Collection<OrderItemDto> getItems() {
        return items;
    }
    public void setItems(Collection<OrderItemDto> items) {
        this.items = items;
    }

    public CustomerDto getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
