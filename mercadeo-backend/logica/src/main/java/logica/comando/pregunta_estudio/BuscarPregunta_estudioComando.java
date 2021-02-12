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

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_preguntaEncuesta().get_descripcion());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject pregunta_estudio = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_preguntaEncuesta().get_descripcion())
                    .add("estado",obj.get_estado()).build();

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
