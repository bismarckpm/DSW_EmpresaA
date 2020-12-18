package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EncuestaResponse {

    private String descripcion;
    private String tipoPregunta;
    //private List<String> NombreRespuesta;

    public EncuestaResponse(String descripcion, String tipoPregunta/*, ArrayList<String> NombreRespuesta*/) {
        this.descripcion = descripcion;
        this.tipoPregunta = tipoPregunta;
        /*this.NombreRespuesta = NombreRespuesta;*/
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    /*public List<String> getNombreRespuesta() {
        return NombreRespuesta;
    }

    public void setNombreRespuesta(List<String> nombreRespuesta) {
        NombreRespuesta = nombreRespuesta;
    }*/
}
