package exception.exceptions.nullExceptions;

public class OrderItemNullException extends ApiNullException {
    public OrderItemNullException(String request) {
        super(request, null, "OrderItem");
    }

    public OrderItemNullException(String request, String layerName) {
        super(request, layerName, "OrderItem");
    }

}
