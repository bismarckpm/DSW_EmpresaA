package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_encuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPregunta_encuestaComando extends BaseComando {

    public List<Pregunta_encuesta> pregunta_encuestas= null;

    @Override
    public void execute() {
        try{
            DaoPregunta_encuesta dao= Fabrica.crear(DaoPregunta_encuesta.class);
            pregunta_encuestas= dao.findAll(Pregunta_encuesta.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las pregunta_encuestas");
        data.setObjeto(pregunta_encuestas);

        return data;
    }

}
