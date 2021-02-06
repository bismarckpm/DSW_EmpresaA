package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarHijoComando extends BaseComando {

    public JsonArrayBuilder hijos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoHijo dao= Fabrica.crear(DaoHijo.class);
        List<Hijo> Lista= dao.findAll(Hijo.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        for(Hijo obj: Lista){

            JsonObject hijo = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_fechaNacimiento", dateFormat.format(obj.get_fechaNacimiento()))
                    .add("_genero",obj.get_genero())
                    .add("_estado",obj.get_estado())
                    .add("_datoUsuario",obj.get_datoUsuario().get_id()).build();

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
