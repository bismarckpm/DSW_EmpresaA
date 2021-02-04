package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaPreguntaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarRespuesta_preguntaComando extends BaseComando {

    public Respuesta_preguntaDto respuesta_preguntaDto;
    public JsonObject respuesta_preguntaJson;
    public long _id;

    public ConsultarRespuesta_preguntaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta respuesta_pregunta = dao.find(_id,Respuesta_pregunta.class);
            this.respuesta_preguntaDto= RespuestaPreguntaMapper.mapEntityToDto(respuesta_pregunta);

            respuesta_preguntaJson= Json.createObjectBuilder()
                    .add("_id",respuesta_pregunta.get_id())
                    .add("_nombre",respuesta_pregunta.get_nombre())
                    .add("_estado",respuesta_pregunta.get_estado())
                    .add("_preguntaEncuesta",respuesta_pregunta.get_preguntaEncuesta().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta_pregunta consultada")
                .add("respuesta_pregunta",respuesta_preguntaJson).build();

        return data;
    }
}
