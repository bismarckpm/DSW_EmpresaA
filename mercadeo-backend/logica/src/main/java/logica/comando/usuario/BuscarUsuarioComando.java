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

            JsonObject usuario = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombreUsuario",obj.get_nombreUsuario())
                    .add("_correo",obj.get_correo())
                    .add("_password",obj.get_password())
                    .add("_codigoRecuperacion",obj.get_codigoRecuperacion())
                    .add("_token",obj.get_token())
                    .add("_estado",obj.get_estado())
                    .add("_rol",obj.get_rol().get_id())
                    .add("_datoUsuario",obj.get_datoUsuario().get_id()).build();

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
