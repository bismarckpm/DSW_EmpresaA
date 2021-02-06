package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarDato_usuarioComando extends BaseComando {

    public JsonArrayBuilder dato_usuarios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoDato_usuario dao= Fabrica.crear(DaoDato_usuario.class);
        List<Dato_usuario> Lista= dao.findAll(Dato_usuario.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        for(Dato_usuario obj: Lista){

            JsonObject dato_usuario = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_cedula",obj.get_cedula())
                    .add("_primerNombre", obj.get_primerNombre())
                    .add("_segundoNombre", obj.get_segundoNombre())
                    .add("_primerApellido", obj.get_primerApellido())
                    .add("_segundoApellido", obj.get_segundoApellido())
                    .add("_sexo", obj.get_sexo())
                    .add("_fechaNacimiento", dateFormat.format(obj.get_fechaNacimiento()))
                    .add("_estadoCivil", obj.get_estadoCivil())
                    .add("_disponibilidadEnLinea", obj.get_disponibilidadEnLinea())
                    .add("_conCuantasPersonasVive", obj.get_conCuantasPersonasVive())
                    .add("_medioComunicacion", obj.get_medioComunicacion())
                    .add("_estado",obj.get_estado())
                    .add("_nivelEconomico", obj.get_nivelEconomico().get_id())
                    .add("_lugar", obj.get_lugar().get_id())
                    .add("_ocupacion", obj.get_ocupacion().get_id())
                    .add("_nivelAcademico", obj.get_nivelAcademico().get_id()).build();

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
