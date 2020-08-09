package service;

import dto.product.ProductDto;
import util.Pageable;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    ProductDto getProduct(long id);
    Collection<ProductDto> getProducts();
    Collection<ProductDto> getProductByPage(Pageable pageable);
    void saveProduct(ProductDto product);
    void updateProduct(ProductDto product);
    void deleteProduct(long id);
}
