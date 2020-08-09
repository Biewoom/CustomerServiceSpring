package exception;

public class ApiException extends RuntimeException {
    private final String param;
    private final long value;

    public ApiException(String param, long value) {
        this.param = param;
        this.value = value;
    }

    public ApiException(String message, String param, long value) {
        super(message);
        this.param = param;
        this.value = value;
    }
//    Getters
    public String getParam() {
        return param;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ApiException{" +
               "param='" + param + '\'' +
               ", value=" + value +
               '}';
    }
}
