package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPregunta_estudioComando extends BaseComando {

    public JsonArrayBuilder pregunta_estudios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoPregunta_estudio dao= Fabrica.crear(DaoPregunta_estudio.class);
        List<Pregunta_estudio> Lista= dao.findAll(Pregunta_estudio.class);

        for(Pregunta_estudio obj: Lista){

            JsonObject pregunta_estudio = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_pregunta",obj.get_pregunta())
                    .add("_estado",obj.get_estado())
                    .add("_estudio",obj.get_estudio().get_id())
                    .add("_preguntaEncuesta",obj.get_preguntaEncuesta().get_id()).build();

            pregunta_estudios.add(pregunta_estudio);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las pregunta_estudios")
                .add("estado","Éxito")
                .add("pregunta_estudios",pregunta_estudios).build();

        return data;
    }

}
