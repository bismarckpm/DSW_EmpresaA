package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarUsuarioComando extends BaseComando {

    public JsonArrayBuilder usuarios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoUsuario dao= Fabrica.crear(DaoUsuario.class);
        List<Usuario> Lista= dao.findAll(Usuario.class);

        for(Usuario obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombreUsuario());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject usuario = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombreUsuario())
                    .add("estado",obj.get_estado()).build();

            usuarios.add(usuario);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los usuarios")
                .add("estado","Éxito")
                .add("usuarios",usuarios).build();

        return data;
    }

}
