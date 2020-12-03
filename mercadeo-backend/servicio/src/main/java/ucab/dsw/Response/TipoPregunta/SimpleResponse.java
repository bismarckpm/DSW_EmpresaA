package ucab.dsw.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleResponse {
    private String pregunta;
    private List<SolucionResponse> respuesta;
}
