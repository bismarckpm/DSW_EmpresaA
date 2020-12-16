package ucab.dsw.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CerradaResponse {
    private String pregunta;

    public CerradaResponse(String pregunta) {
        this.pregunta = pregunta;
    }
}
