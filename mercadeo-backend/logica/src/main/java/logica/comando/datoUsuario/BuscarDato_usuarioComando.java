package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarDato_usuarioComando extends BaseComando {

    public JsonArrayBuilder dato_usuarios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoDato_usuario dao= Fabrica.crear(DaoDato_usuario.class);
        List<Dato_usuario> Lista= dao.findAll(Dato_usuario.class);

        for(Dato_usuario obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_primerNombre() + " " + obj.get_primerApellido());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject dato_usuario = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_primerNombre() + " " + obj.get_primerApellido())
                    .add("estado",obj.get_estado()).build();

            dato_usuarios.add(dato_usuario);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los dato_usuarios")
                .add("estado","Éxito")
                .add("dato_usuarios",dato_usuarios).build();

        return data;
    }

}
