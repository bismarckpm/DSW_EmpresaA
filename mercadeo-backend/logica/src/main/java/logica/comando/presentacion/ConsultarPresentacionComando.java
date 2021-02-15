package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarPresentacionComando extends BaseComando {

    public Presentacion presentacion;
    public long _id;

    public ConsultarPresentacionComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de una presentación
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoPresentacion dao = new DaoPresentacion();
            this.presentacion = dao.find(_id, Presentacion.class);

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
        data.setMensaje("Presentacion consultada");
        data.setObjeto(this.presentacion);

        return data;
    }
}
