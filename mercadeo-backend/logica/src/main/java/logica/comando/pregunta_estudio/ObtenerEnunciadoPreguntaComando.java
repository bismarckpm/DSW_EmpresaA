package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerEnunciadoPreguntaComando extends BaseComando {

    public List<Pregunta_encuesta> pregunta_encuesta = null;
    public long id;

    public ObtenerEnunciadoPreguntaComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{

        try{
            DaoPregunta_estudio dao= Fabrica.crear(DaoPregunta_estudio.class);
            DaoPregunta_encuesta daoPregunta_encuesta = Fabrica.crear(DaoPregunta_encuesta.class);
            pregunta_encuesta = daoPregunta_encuesta.getEnunciadoPregunta(dao.find(id, Pregunta_estudio.class));

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
        data.setMensaje("Cargando estudios respondidos");
        data.setObjeto(this.pregunta_encuesta);

        return data;
    }
}
