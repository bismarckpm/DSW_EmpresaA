package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsuarioRespondieronEstudioResponse {
    private String nombre;
    private String nombreEstudio;
    private String fechaInicioEstudio;
    private String fechaFinEstudio;

    public UsuarioRespondieronEstudioResponse(String nombre, String nombreEstudio, String fechaInicioEstudio, String fechaFinEstudio) {
        this.nombre = nombre;
        this.nombreEstudio = nombreEstudio;
        this.fechaInicioEstudio = fechaInicioEstudio;
        this.fechaFinEstudio = fechaFinEstudio;
    }
}
