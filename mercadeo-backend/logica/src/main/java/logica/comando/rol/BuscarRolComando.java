package logica.comando.rol;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Rol;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRolComando extends BaseComando {

    public JsonArrayBuilder rols= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoRol dao= Fabrica.crear(DaoRol.class);
        List<Rol> Lista= dao.findAll(Rol.class);

        for(Rol obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject rol = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

            rols.add(rol);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los roles")
                .add("estado","Éxito")
                .add("roles",rols).build();

        return data;
    }

}
