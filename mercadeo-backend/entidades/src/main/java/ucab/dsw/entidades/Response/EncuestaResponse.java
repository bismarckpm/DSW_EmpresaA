package ucab.dsw.entidades.Response;

import lombok.Getter;
import lombok.Setter;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EncuestaResponse {

    private long idPreguntaEncuesta;
    private String descripcion;
    private String tipoPregunta;
    private long idPreguntaEstudio;

    public EncuestaResponse(long idPreguntaEncuesta, String descripcion, String tipoPregunta, long idPreguntaEstudio) {
        this.idPreguntaEncuesta = idPreguntaEncuesta;
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
        this.idPreguntaEstudio = idPreguntaEstudio;
    }
}
