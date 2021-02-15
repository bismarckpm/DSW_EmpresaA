package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerSolicitudUsuarioComando extends BaseComando {

    public List<Solicitud_estudio> solicitud_estudios  = null;
    public long _id;

    public ObtenerSolicitudUsuarioComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de las solicitudes de un cliente
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            solicitud_estudios = dao.solicitudesCliente(_id);
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
        data.setMensaje("Solicitud consultado");
        data.setObjeto(this.solicitud_estudios);
        return data;
    }
}
