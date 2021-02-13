package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;
import ucab.dsw.entidades.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class RespuestasEncuestaComando extends BaseComando {

    public List<Respuesta_preguntaResponse> ResponseListUpdate = null;
    public long _id;

    public RespuestasEncuestaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta daoRespuesta = Fabrica.crear(DaoRespuesta.class);
            List<Object[]> respuestas = daoRespuesta.listarRespuestaEncuesta(_id);

            ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {
                ResponseListUpdate.add(new Respuesta_preguntaResponse((Long)r[0], (String)r[1]));
            }

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
        data.setObjeto(this.ResponseListUpdate);

        return data;
    }
}
