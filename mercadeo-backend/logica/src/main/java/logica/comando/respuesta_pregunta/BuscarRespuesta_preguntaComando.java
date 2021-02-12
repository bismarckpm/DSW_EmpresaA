package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRespuesta_preguntaComando extends BaseComando {

    public List<Respuesta_pregunta> respuesta_preguntas= null;

    @Override
    public void execute() throws CustomException {
        try{
            DaoRespuesta_pregunta dao= Fabrica.crear(DaoRespuesta_pregunta.class);
            respuesta_preguntas= dao.findAll(Respuesta_pregunta.class);
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
        data.setMensaje("Cargando todas las respuestas a preguntas");
        data.setObjeto(respuesta_preguntas);

        return data;
    }

}
