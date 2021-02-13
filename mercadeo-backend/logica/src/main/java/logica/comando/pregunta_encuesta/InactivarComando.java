package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.CustomException;

public class InactivarComando extends BaseComando {

    public Pregunta_encuesta pregunta_encuesta;
    public long id;


    public InactivarComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_estado( "I" );
            dao.update(this.pregunta_encuesta);

        }catch ( CustomException ex ) {
            throw ex;
        } catch ( Exception ex ) {
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
