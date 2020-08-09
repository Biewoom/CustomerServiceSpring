package dto.orderItem;

import dto.Dto;
import dto.product.ProductDto;

public class OrderItemDto extends Dto {
    private long id;
    private int amount;
    private ProductDto product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) { this.amount = amount; }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
