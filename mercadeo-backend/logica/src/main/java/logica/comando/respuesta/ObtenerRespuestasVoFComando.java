package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerRespuestasVoFComando extends BaseComando {

    public List<Respuesta> respuestas = null;
    public long _id;

    public ObtenerRespuestasVoFComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de las preguntas de tipo verdadero o falso de un estudio
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            DaoPregunta_estudio daoPregunta_estudio = Fabrica.crear(DaoPregunta_estudio.class);
            respuestas = dao.getRespuestasAPreguntaVF(daoPregunta_estudio.find(_id, Pregunta_estudio.class));
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
        data.setObjeto(this.respuestas);
        return data;
    }
}
