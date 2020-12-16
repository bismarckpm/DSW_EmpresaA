package ucab.dsw.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EscalaResponse {
    private String pregunta;


    public EscalaResponse(String pregunta) {
        this.pregunta = pregunta;
    }
}
