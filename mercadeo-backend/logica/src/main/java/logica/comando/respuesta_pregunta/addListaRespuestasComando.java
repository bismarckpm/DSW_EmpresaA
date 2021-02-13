package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.List;

public class addListaRespuestasComando extends BaseComando {

    public List<Respuesta_pregunta> respuesta_pregunta;

    public addListaRespuestasComando(List<Respuesta_pregunta> respuesta_pregunta) {
        this.respuesta_pregunta = respuesta_pregunta;
    }

    @Override
    public void execute() {

        try {
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            for (Respuesta_pregunta respuesta_preguntax : respuesta_pregunta) {
                dao.insert(respuesta_preguntax);
            }
        } catch (Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta_preguntas Añadidos");
        data.setObjeto(this.respuesta_pregunta);

        return data;
    }
}
