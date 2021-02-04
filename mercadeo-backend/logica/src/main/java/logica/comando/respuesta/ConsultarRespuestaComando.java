package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarRespuestaComando extends BaseComando {

    public RespuestaDto respuestaDto;
    public JsonObject respuestaJson;
    public long _id;

    public ConsultarRespuestaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = dao.find(_id,Respuesta.class);
            this.respuestaDto= RespuestaMapper.mapEntityToDtoSingle(respuesta);

            respuestaJson= Json.createObjectBuilder()
                    .add("_id",respuesta.get_id())
                    .add("_pregunta",respuesta.get_pregunta())
                    .add("_estado",respuesta.get_estado())
                    .add("_respuestaSimple",respuesta.get_respuestaSimple())
                    .add("_respuestaMultiple",respuesta.get_respuestaMultiple())
                    .add("_respuestaAbierta",respuesta.get_respuestaAbierta())
                    .add("_escala",respuesta.get_escala())
                    .add("_verdaderoFalso",respuesta.get_verdaderoFalso())
                    .add("_usuario",respuesta.get_usuario().get_id())
                    .add("_preguntaEstudio",respuesta.get_preguntaEstudio().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta consultada")
                .add("respuesta",respuestaJson).build();

        return data;
    }
}
