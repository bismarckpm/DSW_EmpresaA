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

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject lugar = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

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
