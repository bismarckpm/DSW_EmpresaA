package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaPreguntaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRespuesta_preguntaComando extends BaseComando {

    public Respuesta_pregunta respuesta_pregunta;

    public AddRespuesta_preguntaComando(Respuesta_pregunta respuesta_pregunta) {
        this.respuesta_pregunta = respuesta_pregunta;
    }

    @Override
    public void execute() throws CustomException {

        try {
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            dao.insert( this.respuesta_pregunta );
        } catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta_pregunta Añadida");
        data.setObjeto(this.respuesta_pregunta.get_id());

        return data;
    }

}
