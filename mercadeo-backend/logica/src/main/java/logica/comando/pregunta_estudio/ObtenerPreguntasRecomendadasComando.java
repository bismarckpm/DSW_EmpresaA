package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerPreguntasRecomendadasComando extends BaseComando {

    public List<PreguntasResponse> preguntasRecomendadas = null;
    public long id;

    public ObtenerPreguntasRecomendadasComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{

        try{
            DaoPregunta_estudio dao= Fabrica.crear(DaoPregunta_estudio.class);
            preguntasRecomendadas = dao.listarPreguntasRecomendadas(id);
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
        data.setMensaje("Cargando preguntas");
        data.setObjeto(this.preguntasRecomendadas);

        return data;
    }
}
