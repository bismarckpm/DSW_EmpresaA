package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddOcupacionComando extends BaseComando {

    public Ocupacion ocupacion;

    public AddOcupacionComando(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * Este comando ejecuta la inserción de una ocupación
     */
    @Override
    public void execute() throws CustomException{

        try {
            DaoOcupacion dao = Fabrica.crear(DaoOcupacion.class);
            dao.insert( this.ocupacion );
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
        data.setMensaje("Ocupacion Añadida");
        data.setObjeto(this.ocupacion);

        return data;
    }

}
