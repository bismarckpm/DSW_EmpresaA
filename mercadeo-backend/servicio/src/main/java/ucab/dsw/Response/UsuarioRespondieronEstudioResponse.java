package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsuarioRespondieronEstudioResponse {
    private String nombre;
    private String nombreEstudio;
    private Date fechaInicioEstudio;
    private Date fechaFinEstudio;

    public UsuarioRespondieronEstudioResponse(String nombre, String nombreEstudio, Date fechaInicioEstudio, Date fechaFinEstudio) {
        this.nombre = nombre;
        this.nombreEstudio = nombreEstudio;
        this.fechaInicioEstudio = fechaInicioEstudio;
        this.fechaFinEstudio = fechaFinEstudio;
    }
}
