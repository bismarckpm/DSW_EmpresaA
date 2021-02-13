package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;

public class InactivarSolicitudComando extends BaseComando {

    public Solicitud_estudio solicitud_estudio;
    public long id;


    public InactivarSolicitudComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            solicitud_estudio = dao.find(id, Solicitud_estudio.class);
            solicitud_estudio.set_estado( "I" );
            dao.update( solicitud_estudio );

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
        data.setMensaje("Pregunta_encuesta actualizada");
        data.setObjeto(this.solicitud_estudio);

        return data;
    }
}
