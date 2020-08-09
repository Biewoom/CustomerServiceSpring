package exception.exceptions.nullExceptions;

public class ProductNullException extends ApiNullException {
    public ProductNullException(String request) {
        super(request, null, "Product");
    }

    public ProductNullException(String request, String layerName) {
        super(request, layerName, "Product");
    }
}
