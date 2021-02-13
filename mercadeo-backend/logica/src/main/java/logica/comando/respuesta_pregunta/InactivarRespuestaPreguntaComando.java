package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

public class InactivarRespuestaPreguntaComando extends BaseComando {

    public Respuesta_pregunta respuesta_pregunta;
    public long id;


    public InactivarRespuestaPreguntaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            respuesta_pregunta = dao.find(id, Respuesta_pregunta.class);
            respuesta_pregunta.set_estado( "I" );
            dao.update(this.respuesta_pregunta);

        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Pregunta_encuesta actualizada");
        data.setObjeto(this.respuesta_pregunta);

        return data;
    }
}
