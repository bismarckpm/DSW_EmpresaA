package ucab.dsw.entidades.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Respuesta_preguntaResponse {
    private long fkPregunta;
    private String pregunta;

    public Respuesta_preguntaResponse(long fkPregunta, String pregunta) {
        this.fkPregunta = fkPregunta;
        this.pregunta = pregunta;
    }
}
