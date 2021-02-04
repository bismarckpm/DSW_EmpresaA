package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRespuestaComando extends BaseComando {

    public JsonArrayBuilder respuestas= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoRespuesta dao= Fabrica.crear(DaoRespuesta.class);
        List<Respuesta> Lista= dao.findAll(Respuesta.class);

        for(Respuesta obj: Lista){

            JsonObject respuesta = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_pregunta",obj.get_pregunta())
                    .add("_estado",obj.get_estado())
                    .add("_respuestaSimple",obj.get_respuestaSimple())
                    .add("_respuestaMultiple",obj.get_respuestaMultiple())
                    .add("_respuestaAbierta",obj.get_respuestaAbierta())
                    .add("_escala",obj.get_escala())
                    .add("_verdaderoFalso",obj.get_verdaderoFalso())
                    .add("_usuario",obj.get_usuario().get_id())
                    .add("_preguntaEstudio",obj.get_preguntaEstudio().get_id()).build();

            respuestas.add(respuesta);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las respuestas")
                .add("estado","Éxito")
                .add("respuestas",respuestas).build();

        return data;
    }

}
