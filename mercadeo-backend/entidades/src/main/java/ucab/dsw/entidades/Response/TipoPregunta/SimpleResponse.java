package ucab.dsw.entidades.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleResponse {
    private String pregunta;
    private List<SolucionResponse> respuesta;

    public SimpleResponse(String pregunta, List<SolucionResponse> respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
}
