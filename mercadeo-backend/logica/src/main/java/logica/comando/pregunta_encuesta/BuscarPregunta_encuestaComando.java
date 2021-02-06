package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPregunta_encuestaComando extends BaseComando {

    public JsonArrayBuilder pregunta_encuestas= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoPregunta_encuesta dao= Fabrica.crear(DaoPregunta_encuesta.class);
        List<Pregunta_encuesta> Lista= dao.findAll(Pregunta_encuesta.class);

        for(Pregunta_encuesta obj: Lista){

            JsonObject pregunta_encuesta = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_descripcion",obj.get_descripcion())
                    .add("_tipoPregunta",obj.get_tipoPregunta())
                    .add("_estado",obj.get_estado())
                    .add("_subcategoria",obj.get_subcategoria().get_id())
                    .add("_usuario",obj.get_usuario().get_id()).build();

            pregunta_encuestas.add(pregunta_encuesta);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las pregunta_encuestas")
                .add("estado","Éxito")
                .add("pregunta_encuestas",pregunta_encuestas).build();

        return data;
    }

}
