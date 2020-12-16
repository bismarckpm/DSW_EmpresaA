package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudioUsuarioResponse {

    private long idUsuario;
    private String correo;
    private String nombreUsuario;

    public EstudioUsuarioResponse(long idUsuario, String correo, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }
}
