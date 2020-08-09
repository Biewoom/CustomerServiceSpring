package util;

import domain.entity.AbstractEntity;
import domain.entity.Entity;
import domain.entity.customer.CustomerEntity;
import domain.entity.order.OrderEntity;
import domain.entity.orderItem.OrderItemEntity;
import domain.entity.product.ProductEntity;
import dto.AbstractDto;
import dto.Dto;
import dto.customer.CustomerDto;
import dto.error.NullErrorDto;
import dto.order.OrderDto;
import dto.orderItem.OrderItemDto;
import dto.product.ProductDto;
import exception.exceptions.nullExceptions.ApiNullException;

import java.util.*;

public class Converter {

    public static <E extends AbstractDto, T extends AbstractEntity> Collection<E> collectionConvertEntityToDto(Collection<T> entityCollection, Class<E> outClass){
        Collection<E> dtoCollection = new HashSet<>();
        for(T entity: entityCollection){
            dtoCollection.add((E) EntityToDtoWrapper(entity, outClass));
        }
        return dtoCollection;
    }

    public static <E extends AbstractDto, T extends AbstractEntity> Collection<T> collectionConvertDtoToEntity(Collection<E> dtoCollection, Class<T> outClass) {
        Collection<T> entityCollection = new HashSet<>();
        for(E dto: dtoCollection){
            entityCollection.add((T) DtoToEntityWrapper(dto, outClass));
        }
        return entityCollection;
    }

    //  Dto -> Entity
    private static <E extends AbstractDto> AbstractEntity DtoToEntityWrapper(E dto, Class<? extends AbstractEntity> outclass){
        if (CustomerEntity.class.equals(outclass)) {
            return DtoToEntity((CustomerDto) dto);
        }
        if (ProductEntity.class.equals(outclass)){
            return DtoToEntity((ProductDto) dto);
        }
        if (OrderItemEntity.class.equals(outclass)){
            return DtoToEntity((OrderItemDto) dto);
        }
        if (OrderEntity.class.equals(outclass)){
            return DtoToEntity((OrderDto) dto);
        }
        return new Entity();
    }
    public static CustomerEntity DtoToEntity(CustomerDto customer){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setEmail(customer.getEmail());
        return customerEntity;
    }
    public static ProductEntity DtoToEntity(ProductDto product){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        return productEntity;
    }
    public static OrderItemEntity DtoToEntity(OrderItemDto orderItem){
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(orderItem.getId());
        orderItemEntity.setAmount(orderItem.getAmount());
        orderItemEntity.setProduct(DtoToEntity(orderItem.getProduct()));
        return orderItemEntity;
    }
    public static OrderEntity DtoToEntity(OrderDto order){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setCustomer(DtoToEntity(order.getCustomer()));
        orderEntity.setItems(collectionConvertDtoToEntity(order.getItems(), OrderItemEntity.class));
        return orderEntity;
    }

    // Entity -> Dto
    private static <E extends AbstractEntity> AbstractDto EntityToDtoWrapper(E entity, Class<? extends AbstractDto> outclass){
        if (CustomerDto.class.equals(outclass)) {
            return EntityToDto((CustomerEntity) entity);
        }
        if (ProductDto.class.equals(outclass)){
            return EntityToDto((ProductEntity) entity);
        }
        if (OrderItemDto.class.equals(outclass)){
            return EntityToDto((OrderItemEntity) entity);
        }
        if (OrderDto.class.equals(outclass)){
            return EntityToDto((OrderEntity) entity);
        }
        return new Dto();
    }

    public static CustomerDto EntityToDto(CustomerEntity customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }
    public static ProductDto EntityToDto(ProductEntity product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
    public static OrderItemDto EntityToDto(OrderItemEntity orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setAmount(orderItem.getAmount());
        orderItemDto.setProduct(EntityToDto(orderItem.getProduct()));
        return orderItemDto;
    }
    public static OrderDto EntityToDto(OrderEntity order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setCustomer(EntityToDto(order.getCustomer()));
        orderDto.setItems(collectionConvertEntityToDto(order.getItems(), OrderItemDto.class));
        return orderDto;
    }

    public static NullErrorDto ExceptionToDto(ApiNullException apiNullException){
        NullErrorDto nullErrorDto = new NullErrorDto();
        nullErrorDto.setParam(apiNullException.getParam());
        nullErrorDto.setValue(apiNullException.getValue());
        nullErrorDto.setType("NullError");
        return nullErrorDto;
    }

}
