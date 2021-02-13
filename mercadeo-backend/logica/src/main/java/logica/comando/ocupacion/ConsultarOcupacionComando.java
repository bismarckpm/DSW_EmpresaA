package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarOcupacionComando extends BaseComando {

    public Ocupacion ocupacion;
    public long _id;

    public ConsultarOcupacionComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoOcupacion dao = new DaoOcupacion();
            this.ocupacion = dao.find(_id, Ocupacion.class);

        }catch ( CustomException ex ) {
            throw ex;
        } catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Ocupacion consultada");
        data.setObjeto(this.ocupacion);

        return data;
    }
}
