package domain.entity.orderItem;

import domain.entity.Entity;
import domain.entity.product.ProductEntity;

public class OrderItemEntity extends Entity {
    private long id;
    private int amount;
    private ProductEntity product;

    public OrderItemEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
               "id=" + id +
               ", amount=" + amount +
               ", product=" + product +
               '}';
    }
}
