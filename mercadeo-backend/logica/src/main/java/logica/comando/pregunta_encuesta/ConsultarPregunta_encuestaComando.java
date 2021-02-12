package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarPregunta_encuestaComando extends BaseComando {

    public Pregunta_encuesta pregunta_encuesta;
    public long _id;

    public ConsultarPregunta_encuestaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            this.pregunta_encuesta = dao.find(_id, Pregunta_encuesta.class);

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
        data.setMensaje("Pregunta_encuesta consultada");
        data.setObjeto(this.pregunta_encuesta);

        return data;
    }
}
