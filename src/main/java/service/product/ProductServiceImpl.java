package service.product;

import domain.entity.product.ProductEntity;
import dto.product.ProductDto;
import exception.exceptions.nullExceptions.ProductNullException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;
import service.ProductService;
import util.Converter;
import util.Pageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto getProduct(long id) {
        ProductEntity productEntity = productRepository.findOne(id);
        if(productEntity == null){
            throw new ProductNullException("getProduct " + id, "Service");
        }
        return Converter.EntityToDto(productEntity);
    }

    @Override
    public Collection<ProductDto> getProducts() {
        Collection<ProductEntity> productEntities = productRepository.findAll();
        if(productEntities == null){
            throw new ProductNullException("getProducts", "Service");
        }
        return Converter.collectionConvertEntityToDto(productEntities, ProductDto.class);
    }

    @Override
    public Collection<ProductDto> getProductByPage(Pageable pageable) {
        return null;
    }

    @Override
    public void saveProduct(ProductDto product) {
        ProductEntity productEntity = Converter.DtoToEntity(product);
        productRepository.save(productEntity);
    }

    @Override
    public void updateProduct(ProductDto product) {
        ProductEntity productEntity = Converter.DtoToEntity(product);
        productRepository.update(productEntity);
    }
    @Transactional
    @Override
    public void deleteProduct(long id) {
        if (getProduct(id) != null){
            productRepository.delete(id);
        }
    }
}
