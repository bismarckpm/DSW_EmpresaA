package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPregunta_estudioComando extends BaseComando {
    
    public Pregunta_estudio pregunta_estudio;

    public EditPregunta_estudioComando(Pregunta_estudio pregunta_estudio) {
        this.pregunta_estudio = pregunta_estudio;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoPregunta_estudio dao = Fabrica.crear(DaoPregunta_estudio.class);
            dao.update(this.pregunta_estudio);
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
        data.setMensaje("Pregunta_estudio actualizada");
        data.setObjeto(this.pregunta_estudio);

        return data;
    }

}
