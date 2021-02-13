package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPregunta_encuestaComando extends BaseComando {
    
    public Pregunta_encuesta pregunta_encuesta;

    public EditPregunta_encuestaComando(Pregunta_encuesta pregunta_encuesta) {
        this.pregunta_encuesta = pregunta_encuesta;
    }

    @Override
    public void execute() {
        try{
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            dao.update(this.pregunta_encuesta);
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Pregunta_encuesta actualizada");
        data.setObjeto(this.pregunta_encuesta);

        return data;
    }

}
