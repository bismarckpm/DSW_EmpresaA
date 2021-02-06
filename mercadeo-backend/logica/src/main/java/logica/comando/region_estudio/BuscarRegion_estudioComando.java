package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRegion_estudioComando extends BaseComando {

    public JsonArrayBuilder region_estudios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoRegion_estudio dao= Fabrica.crear(DaoRegion_estudio.class);
        List<Region_estudio> Lista= dao.findAll(Region_estudio.class);

        for(Region_estudio obj: Lista){

            JsonObject region_estudio = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_estado",obj.get_estado())
                    .add("_lugar",obj.get_lugar().get_id())
                    .add("_solicitudEstudio",obj.get_solicitudEstudio().get_id()).build();;

            region_estudios.add(region_estudio);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las region_estudios")
                .add("estado","Éxito")
                .add("region_estudios",region_estudios).build();

        return data;
    }

}
