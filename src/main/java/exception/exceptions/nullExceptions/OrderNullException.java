package exception.exceptions.nullExceptions;

public class OrderNullException extends ApiNullException {
    public OrderNullException(String request) {
        super(request, null, "Order");
    }

    public OrderNullException(String request, String layerName) {
        super(request, layerName, "Order");
    }

}
