package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;

import java.util.List;

public class ContarRespuestasMultiplesComando extends BaseComando {

    public List<Long> cantidad;
    public long _id;

    public ContarRespuestasMultiplesComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            DaoRespuesta daoRespuesta= Fabrica.crear(DaoRespuesta.class);
            cantidad = dao.contarRespuestasMultiples(daoRespuesta.find(_id, Respuesta.class));
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.cantidad);
        return data;
    }
}
