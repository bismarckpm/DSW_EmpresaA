package ucab.dsw.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AbiertaResponse {
    private String pregunta;

    public AbiertaResponse(String pregunta) {
        this.pregunta = pregunta;
    }
}
