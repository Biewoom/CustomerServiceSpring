package domain.entity.customer;

import domain.entity.Entity;
import domain.entity.order.OrderEntity;

import java.util.Collection;

public class CustomerEntity extends Entity {
    private long id;
    private String name;
    private String address;
    private String email;
    private Collection<OrderEntity> orders;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Collection<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
