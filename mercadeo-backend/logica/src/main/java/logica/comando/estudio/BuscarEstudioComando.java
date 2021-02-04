package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarEstudioComando extends BaseComando {

    public JsonArrayBuilder estudios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
        List<Estudio> Lista= dao.findAll(Estudio.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        for(Estudio obj: Lista){

            JsonObject estudio = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_fechaInicio",dateFormat.format(obj.get_fechaInicio()))
                    .add("_fechaFin",dateFormat.format(obj.get_fechaFin()))
                    .add("_estatus",obj.get_estatus())
                    .add("_estado",obj.get_estado())
                    .add("_solicitudEstudio",obj.get_solicitudEstudio().get_id())
                    .add("_usuario",obj.get_usuario().get_id()).build();

            estudios.add(estudio);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los estudios")
                .add("estado","Éxito")
                .add("estudios",estudios).build();

        return data;
    }

}
