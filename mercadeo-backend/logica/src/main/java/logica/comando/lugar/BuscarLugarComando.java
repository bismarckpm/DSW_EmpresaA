package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarLugarComando extends BaseComando {

    public JsonArrayBuilder lugars= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoLugar dao= Fabrica.crear(DaoLugar.class);
        List<Lugar> Lista= dao.findAll(Lugar.class);

        for(Lugar obj: Lista){

            JsonObject lugar = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_tipo",obj.get_tipo())
                    .add("_categoriaSocioEconomica",obj.get_categoriaSocioEconomica())
                    .add("_estado",obj.get_estado())
                    .add("_lugar",obj.get_lugar().get_id()).build();

            lugars.add(lugar);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los lugares")
                .add("estado","Éxito")
                .add("lugares",lugars).build();

        return data;
    }

}
