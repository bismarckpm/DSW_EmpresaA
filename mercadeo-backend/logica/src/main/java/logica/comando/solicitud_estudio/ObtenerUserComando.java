package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Response.SolicitarEstudioResponse;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObtenerUserComando extends BaseComando {

    public List<SolicitarEstudioResponse> solicitudEstudioListUpdate = new ArrayList<>();
    public long _id;

    public ObtenerUserComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoSolicitud_estudio daoSolicitud_estudio = Fabrica.crear(DaoSolicitud_estudio.class);
            List<Solicitud_estudio> solicitudEstudioList = daoSolicitud_estudio.findAll(Solicitud_estudio.class);

            solicitudEstudioList.stream().filter(i->(i.get_usuario().get_id() == _id)).collect(Collectors.toList()).forEach(i->{ });
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
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.solicitudEstudioListUpdate);
        return data;
    }
}
