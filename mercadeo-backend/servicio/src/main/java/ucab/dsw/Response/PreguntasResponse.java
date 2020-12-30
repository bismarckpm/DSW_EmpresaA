package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntasResponse {
    private long idPreguntaEncuesta;
    private String pregunta;
    private String tipoPregunta;

    public PreguntasResponse(long idPreguntaEncuesta, String pregunta, String tipoPregunta) {
        this.idPreguntaEncuesta = idPreguntaEncuesta;
        this.pregunta = pregunta;
        this.tipoPregunta = tipoPregunta;
    }
}
