package ucab.dsw.entidades.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class VerdaderoFalseResponse {
    private String pregunta;

    public VerdaderoFalseResponse(String pregunta) {
        this.pregunta = pregunta;
    }
}
