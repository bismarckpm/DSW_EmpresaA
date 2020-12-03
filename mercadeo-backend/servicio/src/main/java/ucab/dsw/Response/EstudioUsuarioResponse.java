package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudioUsuarioResponse {

    private long id;
    private String correo;
    private String nombreUsuario;

    public EstudioUsuarioResponse(long id, String correo, String nombreUsuario) {
        this.id = id;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }
}
