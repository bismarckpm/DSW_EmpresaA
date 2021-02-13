package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPregunta_estudioComando extends BaseComando {

    public List<Pregunta_estudio> pregunta_estudios= null;

    @Override
    public void execute() {
        try{
            DaoPregunta_estudio dao= Fabrica.crear(DaoPregunta_estudio.class);
            pregunta_estudios= dao.findAll(Pregunta_estudio.class);
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
        data.setObjeto(pregunta_estudios);

        return data;
    }

}
