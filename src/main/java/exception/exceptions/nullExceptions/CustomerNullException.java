package exception.exceptions.nullExceptions;

public class CustomerNullException extends ApiNullException {
    public CustomerNullException(String request) {
        super(request, null, "Customer");
    }

    public CustomerNullException(String request, String layerName) {
        super(request, layerName, "Customer");
    }

}
