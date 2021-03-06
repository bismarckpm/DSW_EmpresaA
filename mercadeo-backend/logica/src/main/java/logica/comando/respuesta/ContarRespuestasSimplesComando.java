package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ContarRespuestasSimplesComando extends BaseComando {

    public List<Long> cantidad;
    public long _id;

    public ContarRespuestasSimplesComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            DaoRespuesta daoRespuesta= Fabrica.crear(DaoRespuesta.class);
            cantidad = dao.contarRespuestasSimples(daoRespuesta.find(_id, Respuesta.class));
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
        data.setMensaje("Respuesta contadas");
        data.setObjeto(this.cantidad);
        return data;
    }
}
