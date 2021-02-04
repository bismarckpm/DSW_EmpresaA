package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConsultarSolicitud_estudioComando extends BaseComando {

    public Solicitud_estudioDto solicitud_estudioDto;
    public JsonObject solicitud_estudioJson;
    public long _id;

    public ConsultarSolicitud_estudioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            Solicitud_estudio solicitud_estudio = dao.find(_id,Solicitud_estudio.class);
            this.solicitud_estudioDto= SolicitudEstudioMapper.mapEntityToDto(solicitud_estudio);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            solicitud_estudioJson= Json.createObjectBuilder()
                    .add("_id",solicitud_estudio.get_id())
                    .add("_descripcionSolicitud",solicitud_estudio.get_descripcionSolicitud())
                    .add("_generoPoblacional",solicitud_estudio.get_generoPoblacional())
                    .add("_fechaPeticion",dateFormat.format(solicitud_estudio.get_fechaPeticion()))
                    .add("_edadMinimaPoblacion",solicitud_estudio.get_edadMinimaPoblacion())
                    .add("_edadMaximaPoblacion",solicitud_estudio.get_edadMaximaPoblacion())
                    .add("_estatus",solicitud_estudio.get_estatus())
                    .add("_estado",solicitud_estudio.get_estado())
                    .add("_conCuantasPersonasVive",solicitud_estudio.get_conCuantasPersonasVive())
                    .add("_disponibilidadEnLinea",solicitud_estudio.get_disponibilidadEnLinea())
                    .add("_nivelEconomico",solicitud_estudio.get_nivelEconomico().get_id())
                    .add("_ocupacion",solicitud_estudio.get_ocupacion().get_id())
                    .add("_usuario",solicitud_estudio.get_usuario().get_id())
                    .add("_producto",solicitud_estudio.get_producto().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Solicitud_estudio consultada")
                .add("solicitud_estudio",solicitud_estudioJson).build();

        return data;
    }
}
