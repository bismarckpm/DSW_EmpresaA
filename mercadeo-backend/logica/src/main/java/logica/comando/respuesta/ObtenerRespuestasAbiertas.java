package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;

import java.util.List;

public class ObtenerRespuestasAbiertas extends BaseComando {

    public List<Respuesta> respuestas = null;
    public long _id;

    public ObtenerRespuestasAbiertas(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            DaoPregunta_estudio daoPregunta_estudio = Fabrica.crear(DaoPregunta_estudio.class);
            respuestas = dao.getRespuestasAPreguntaAbierta(daoPregunta_estudio.find(_id, Pregunta_estudio.class));
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
        data.setObjeto(this.respuestas);
        return data;
    }
}
