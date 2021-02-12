package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarHijoComando extends BaseComando {

    public JsonArrayBuilder hijos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoHijo dao= Fabrica.crear(DaoHijo.class);
        List<Hijo> Lista= dao.findAll(Hijo.class);

        for(Hijo obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_datoUsuario().get_primerNombre() + " " + obj.get_datoUsuario().get_primerApellido());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject hijo = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_datoUsuario().get_primerNombre() + " "+ obj.get_datoUsuario().get_primerApellido())
                    .add("estado",obj.get_estado()).build();

            hijos.add(hijo);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los hijos")
                .add("estado","Éxito")
                .add("hijos",hijos).build();

        return data;
    }

}
