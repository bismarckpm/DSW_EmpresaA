package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApi {

    private int code;
    private Object data;
    private String status;
    private String message;

    public RestApi(int code, Object data, String status, String message) {
        this.code = code;
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
