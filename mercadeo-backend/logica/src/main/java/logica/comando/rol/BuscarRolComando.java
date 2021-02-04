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

            JsonObject rol = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_descripcion",obj.get_descripcion())
                    .add("_estado",obj.get_estado()).build();

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
