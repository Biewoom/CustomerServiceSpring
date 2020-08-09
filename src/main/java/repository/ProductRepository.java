package repository;

import domain.entity.product.ProductEntity;
import util.Pageable;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    ProductEntity findOne(long id);
    Collection<ProductEntity> findAll();
    Collection<ProductEntity> findAll(Pageable pageable);
    void update(ProductEntity product);
    void save(ProductEntity product);
    void delete(long id);
}
