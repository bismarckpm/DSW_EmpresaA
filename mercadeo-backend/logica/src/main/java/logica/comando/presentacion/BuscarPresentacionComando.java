package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPresentacionComando extends BaseComando {

    public JsonArrayBuilder presentacions= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoPresentacion dao= Fabrica.crear(DaoPresentacion.class);
        List<Presentacion> Lista= dao.findAll(Presentacion.class);

        for(Presentacion obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_titulo());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject presentacion = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_titulo())
                    .add("estado",obj.get_estado()).build();

            presentacions.add(presentacion);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las presentaciones")
                .add("estado","Éxito")
                .add("presentaciones",presentacions).build();

        return data;
    }

}