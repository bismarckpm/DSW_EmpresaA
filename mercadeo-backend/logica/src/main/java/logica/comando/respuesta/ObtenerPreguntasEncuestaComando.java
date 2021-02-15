package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ObtenerPreguntasEncuestaComando extends BaseComando {

    public List<EncuestaResponse> ResponseListUpdate = null;
    public long id1;
    public long id2;

    public ObtenerPreguntasEncuestaComando(long id1, long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public void execute()throws CustomException {

        try{
            DaoRespuesta daoRespuesta = Fabrica.crear(DaoRespuesta.class);
            List<Object[]> preguntas_respuestas = daoRespuesta.listarPreguntaEncuesta(id1, id2);

            ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new EncuestaResponse((long)r[0], (String)r[1], (String)r[2], (long)r[3]));
            }
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
        data.setMensaje("Cargando preguntas");
        data.setObjeto(this.ResponseListUpdate);

        return data;
    }
}
