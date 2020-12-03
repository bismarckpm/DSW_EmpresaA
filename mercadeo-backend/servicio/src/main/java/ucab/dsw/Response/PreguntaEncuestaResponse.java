package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaEncuestaResponse {
    private long id;
    private String descripcion;
    private String tipoPregunta;

    public PreguntaEncuestaResponse(long id, String descripcion, String tipoPregunta) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
    }
}
