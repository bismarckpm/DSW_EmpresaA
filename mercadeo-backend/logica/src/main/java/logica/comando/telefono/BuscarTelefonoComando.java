package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Telefono;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarTelefonoComando extends BaseComando {

    public JsonArrayBuilder telefonos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoTelefono dao= Fabrica.crear(DaoTelefono.class);
        List<Telefono> Lista= dao.findAll(Telefono.class);

        for(Telefono obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_numero());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject telefono = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_numero())
                    .add("estado",obj.get_estado()).build();

            telefonos.add(telefono);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los telefonos")
                .add("estado","Éxito")
                .add("telefonos",telefonos).build();

        return data;
    }

}
