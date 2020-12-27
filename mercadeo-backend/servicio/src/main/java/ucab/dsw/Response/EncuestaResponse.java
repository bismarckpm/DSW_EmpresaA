package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EncuestaResponse {

    private long id;
    private String descripcion;
    private String tipoPregunta;

    public EncuestaResponse(long id, String descripcion, String tipoPregunta) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
    }
}
