package exception.exceptions.nullExceptions;

import exception.ApiException;

public class ApiNullException extends ApiException {
    public ApiNullException(String request) {  super(request);
    }

    public ApiNullException(String request, String layerName) {
        super(request, layerName);
    }

    public ApiNullException(String request, String layerName, String serviceName) {
        super(request, layerName, serviceName);
    }
}
