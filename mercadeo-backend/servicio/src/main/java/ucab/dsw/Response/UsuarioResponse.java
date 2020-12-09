package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private long id;
    private String nombreUsuario;
    private String correo;
    private long idRol;
    private String estado;

    public UsuarioResponse(long id, String nombreUsuario, String correo, long idRol, String estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.idRol = idRol;
        this.estado = estado;
    }
}
