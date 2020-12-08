package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRestResponse {
    private int code;
    private Object data;
    private String status;
    private String message;

    public ApiRestResponse(int code, Object data, String status, String message) {
        this.code = code;
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
