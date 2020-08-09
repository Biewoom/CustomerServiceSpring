package domain.entity.order;

import domain.entity.Entity;
import domain.entity.orderItem.OrderItemEntity;
import domain.entity.customer.CustomerEntity;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class OrderEntity extends Entity {
    private long id;
    private Date orderDate;
    private Collection<OrderItemEntity> items;
    private CustomerEntity customer;

    public OrderEntity(){
        super();
    }

    public Collection<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItemEntity> items) {
        this.items = items;
    }

    public CustomerEntity getCustomer() { return customer; }

    public void setCustomer(CustomerEntity customer) {
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

    @Override
    public String toString() {
        return "OrderEntity{" +
               "id=" + id +
               ", orderDate=" + orderDate +
               ", customer=" + customer +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
