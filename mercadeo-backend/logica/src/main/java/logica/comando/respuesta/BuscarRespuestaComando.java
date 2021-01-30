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

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_pregunta());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject respuesta = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre_pregunta",obj.get_pregunta())
                    .add("estado",obj.get_estado()).build();

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
