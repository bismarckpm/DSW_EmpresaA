package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarSolicitud_estudioComando extends BaseComando {

    public JsonArrayBuilder solicitud_estudios= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoSolicitud_estudio dao= Fabrica.crear(DaoSolicitud_estudio.class);
        List<Solicitud_estudio> Lista= dao.findAll(Solicitud_estudio.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        for(Solicitud_estudio obj: Lista){

            JsonObject solicitud_estudio = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_descripcionSolicitud",obj.get_descripcionSolicitud())
                    .add("_generoPoblacional",obj.get_generoPoblacional())
                    .add("_fechaPeticion",dateFormat.format(obj.get_fechaPeticion()))
                    .add("_edadMinimaPoblacion",obj.get_edadMinimaPoblacion())
                    .add("_edadMaximaPoblacion",obj.get_edadMaximaPoblacion())
                    .add("_estatus",obj.get_estatus())
                    .add("_estado",obj.get_estado())
                    .add("_conCuantasPersonasVive",obj.get_conCuantasPersonasVive())
                    .add("_disponibilidadEnLinea",obj.get_disponibilidadEnLinea())
                    .add("_nivelEconomico",obj.get_nivelEconomico().get_id())
                    .add("_ocupacion",obj.get_ocupacion().get_id())
                    .add("_usuario",obj.get_usuario().get_id())
                    .add("_producto",obj.get_producto().get_id()).build();

            solicitud_estudios.add(solicitud_estudio);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las solicitud_estudios")
                .add("estado","Éxito")
                .add("solicitud_estudios",solicitud_estudios).build();

        return data;
    }

}
