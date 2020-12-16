package ucab.dsw.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultipleResponse {
    private String pregunta;
    private List<SolucionResponse> respuesta;

    public MultipleResponse(String pregunta, List<SolucionResponse> respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
}
