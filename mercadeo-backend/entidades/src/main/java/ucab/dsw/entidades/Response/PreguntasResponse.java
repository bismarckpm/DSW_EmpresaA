package ucab.dsw.entidades.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntasResponse {
    private long idPreguntaEncuesta;
    private String pregunta;
    private String tipoPregunta;
    private String subcategoria;
    private long idPreguntaEstudio;

    public PreguntasResponse(long idPreguntaEncuesta, String pregunta, String tipoPregunta
                             ,String subcategoria, long idPreguntaEstudio ) {
        this.idPreguntaEncuesta = idPreguntaEncuesta;
        this.pregunta = pregunta;
        this.tipoPregunta = tipoPregunta;
        this.subcategoria = subcategoria;
        this.idPreguntaEstudio = idPreguntaEstudio;
    }
}
