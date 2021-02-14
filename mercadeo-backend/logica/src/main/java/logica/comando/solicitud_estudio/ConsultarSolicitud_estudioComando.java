package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConsultarSolicitud_estudioComando extends BaseComando {

    public Solicitud_estudio solicitud_estudio;
    public long _id;

    public ConsultarSolicitud_estudioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            this.solicitud_estudio = dao.find(_id, Solicitud_estudio.class);

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Solicitud de estudio consultada");
        data.setObjeto(this.solicitud_estudio);

        return data;
    }
}
