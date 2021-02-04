package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaPreguntaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRespuesta_preguntaComando extends BaseComando {

    public Respuesta_pregunta respuesta_pregunta;

    public AddRespuesta_preguntaComando(Respuesta_pregunta respuesta_pregunta) {
        this.respuesta_pregunta = respuesta_pregunta;
    }

    @Override
    public void execute() {

        try {
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            Respuesta_pregunta resul = dao.insert( this.respuesta_pregunta );
            this.respuesta_pregunta=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta_pregunta añadida")
                .add("respuesta_pregunta_id",this.respuesta_pregunta.get_id()).build();

        return data;
    }

}
