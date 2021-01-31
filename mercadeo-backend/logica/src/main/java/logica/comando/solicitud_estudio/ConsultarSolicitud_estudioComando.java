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

            solicitud_estudioJson= Json.createObjectBuilder()
                    .add("id",solicitud_estudio.get_id())
                    .add("nombre",solicitud_estudio.get_descripcionSolicitud())
                    .add("estado",solicitud_estudio.get_estado()).build();

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
