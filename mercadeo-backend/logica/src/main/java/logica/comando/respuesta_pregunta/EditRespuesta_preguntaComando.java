package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.RespuestaPreguntaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRespuesta_preguntaComando extends BaseComando {
    
    public Respuesta_pregunta respuesta_pregunta;

    public EditRespuesta_preguntaComando(Respuesta_pregunta respuesta_pregunta) {
        this.respuesta_pregunta = respuesta_pregunta;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            dao.update(this.respuesta_pregunta);
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta_pregunta actualizada");
        data.setObjeto(this.respuesta_pregunta);

        return data;
    }

}
