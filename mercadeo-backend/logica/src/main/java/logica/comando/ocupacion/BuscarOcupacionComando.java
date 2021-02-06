package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarOcupacionComando extends BaseComando {

    public JsonArrayBuilder ocupacions= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoOcupacion dao= Fabrica.crear(DaoOcupacion.class);
        List<Ocupacion> Lista= dao.findAll(Ocupacion.class);

        for(Ocupacion obj: Lista){

            JsonObject ocupacion = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_estado",obj.get_estado()).build();

            ocupacions.add(ocupacion);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las ocupaciones")
                .add("estado","Éxito")
                .add("ocupaciones",ocupacions).build();

        return data;
    }

}
