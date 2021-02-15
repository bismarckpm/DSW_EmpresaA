package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerEstudiosSolicitudComando extends BaseComando {

    public List<Estudio> salida = null;
    public long _id;

    public ObtenerEstudiosSolicitudComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            Solicitud_estudio solicitud_estudio = dao.find(_id, Solicitud_estudio.class);
            DaoEstudio daoEst = Fabrica.crear(DaoEstudio.class);
            salida = daoEst.getEstudioPorSolicitud(_id);

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
        data.setMensaje("Estudio consultado");
        data.setObjeto(this.salida);
        return data;
    }
}
