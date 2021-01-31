package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarSolicitud_estudioComando extends BaseComando {

    public JsonArrayBuilder solicitud_estudios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoSolicitud_estudio dao= Fabrica.crear(DaoSolicitud_estudio.class);
        List<Solicitud_estudio> Lista= dao.findAll(Solicitud_estudio.class);

        for(Solicitud_estudio obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_descripcionSolicitud());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject solicitud_estudio = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_descripcionSolicitud())
                    .add("estado",obj.get_estado()).build();

            solicitud_estudios.add(solicitud_estudio);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las solicitud_estudios")
                .add("estado","Éxito")
                .add("solicitud_estudios",solicitud_estudios).build();

        return data;
    }

}
