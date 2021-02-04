package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditSolicitud_estudioComando extends BaseComando {

    public long _id;
    public Solicitud_estudio solicitud_estudio;

    public EditSolicitud_estudioComando(long _id, Solicitud_estudio solicitud_estudio) {
        this._id = _id;
        this.solicitud_estudio = solicitud_estudio;
    }

    @Override
    public void execute() {
        try{
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            Solicitud_estudio resul = dao.update(solicitud_estudio);
            this.solicitud_estudio=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Solicitud_estudio actualizada")
                .add("solicitud_estudio_descripcion",this.solicitud_estudio.get_id()).build();

        return data;
    }

}
