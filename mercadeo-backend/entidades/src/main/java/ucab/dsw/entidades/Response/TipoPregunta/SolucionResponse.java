package ucab.dsw.entidades.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolucionResponse {
    private String respuesta;
    private long id;
    private String valor;

    public SolucionResponse(String respuesta, long id, String valor) {
        this.respuesta = respuesta;
        this.id = id;
        this.valor = valor;
    }
}

