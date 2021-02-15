package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditOcupacionComando extends BaseComando {
    
    public Ocupacion ocupacion;

    public EditOcupacionComando(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoOcupacion dao = Fabrica.crear(DaoOcupacion.class);
            dao.update(this.ocupacion);
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
        data.setMensaje("Ocupacion actualizada");
        data.setObjeto(this.ocupacion);

        return data;
    }

}
