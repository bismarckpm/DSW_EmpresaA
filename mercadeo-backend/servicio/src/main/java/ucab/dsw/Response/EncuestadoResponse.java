package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncuestadoResponse {
    private String nombre;
    private String correo;
    private String nombreUsuario;

    public EncuestadoResponse(String nombre, String correo, String nombreUsuario) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }
}
