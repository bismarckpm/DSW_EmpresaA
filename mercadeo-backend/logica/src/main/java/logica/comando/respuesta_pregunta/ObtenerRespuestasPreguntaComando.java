package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerRespuestasPreguntaComando extends BaseComando {

    public List<Respuesta_pregunta> respuesta_preguntas = null;
    public long id;

    public ObtenerRespuestasPreguntaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute()throws CustomException {

        try{
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            DaoPregunta_encuesta daoPregunta = Fabrica.crear(DaoPregunta_encuesta.class);
            respuesta_preguntas = dao.getRespuestasPregunta(daoPregunta.find(id, Pregunta_encuesta.class));
        }catch ( CustomException ex ) {
            throw ex;
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando estudios respondidos");
        data.setObjeto(this.respuesta_preguntas);

        return data;
    }
}
