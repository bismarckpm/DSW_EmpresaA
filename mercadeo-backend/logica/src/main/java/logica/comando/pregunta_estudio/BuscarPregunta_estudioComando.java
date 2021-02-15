package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPregunta_estudioComando extends BaseComando {

    public List<Pregunta_estudio> pregunta_estudios= null;

    /**
     * Este comando ejecuta la consulta de todas las preguntas asignadas a estudios
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoPregunta_estudio dao= Fabrica.crear(DaoPregunta_estudio.class);
            pregunta_estudios= dao.findAll(Pregunta_estudio.class);
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
        data.setMensaje("Cargando todas las pregunta_estudios");
        data.setObjeto(this.pregunta_estudios);

        return data;
    }

}
