package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRespuesta_preguntaComando extends BaseComando {

    public JsonArrayBuilder respuesta_preguntas= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoRespuesta_pregunta dao= Fabrica.crear(DaoRespuesta_pregunta.class);
        List<Respuesta_pregunta> Lista= dao.findAll(Respuesta_pregunta.class);

        for(Respuesta_pregunta obj: Lista){

            JsonObject respuesta_pregunta = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_estado",obj.get_estado())
                    .add("_preguntaEncuesta",obj.get_preguntaEncuesta().get_id()).build();

            respuesta_preguntas.add(respuesta_pregunta);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las respuesta_preguntas")
                .add("estado","Éxito")
                .add("respuesta_preguntas",respuesta_preguntas).build();

        return data;
    }

}
