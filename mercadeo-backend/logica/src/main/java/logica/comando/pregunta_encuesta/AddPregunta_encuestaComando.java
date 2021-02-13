package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPregunta_encuestaComando extends BaseComando {

    public Pregunta_encuesta pregunta_encuesta;

    public AddPregunta_encuestaComando(Pregunta_encuesta pregunta_encuesta) {
        this.pregunta_encuesta = pregunta_encuesta;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            dao.insert( this.pregunta_encuesta );
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
        data.setMensaje("Pregunta_encuesta Añadida");
        data.setObjeto(this.pregunta_encuesta);

        return data;
    }

}
