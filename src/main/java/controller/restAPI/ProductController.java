package controller.restAPI;

import dto.product.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apis/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}", produces = "application/json")
    public ProductDto getProductById(@PathVariable("id") long id){
        return productService.getProduct(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public Collection<ProductDto> getProducts(){
        return productService.getProducts();
    }
    // get by name

    // get by price lt, gt
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void create(@RequestBody ProductDto product){
        productService.saveProduct(product);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ProductDto product){
        productService.updateProduct(product);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id){
        productService.deleteProduct(id);
    }
}
