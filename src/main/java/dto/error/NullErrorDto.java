package dto.error;

public class NullErrorDto {
    private String param;
    private long value;
    private String Type;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
