package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdDatosUsuarioResponse {
    private String id;

    public IdDatosUsuarioResponse(String id) {
        this.id = id;
    }
}
