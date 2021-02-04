package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddSolicitud_estudioComando extends BaseComando {

    public Solicitud_estudio solicitud_estudio;

    public AddSolicitud_estudioComando(Solicitud_estudio solicitud_estudio) {
        this.solicitud_estudio = solicitud_estudio;
    }

    @Override
    public void execute() {

        try {
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            Solicitud_estudio resul = dao.insert( this.solicitud_estudio );
            this.solicitud_estudio=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Solicitud_estudio añadida")
                .add("solicitud_estudio_id",this.solicitud_estudio.get_id()).build();

        return data;
    }

}
