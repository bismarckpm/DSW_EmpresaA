package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarOcupacionComando extends BaseComando {

    public List<Ocupacion> ocupacions= null;

    @Override
    public void execute() throws CustomException {
        try{
            DaoOcupacion dao= Fabrica.crear(DaoOcupacion.class);
            ocupacions= dao.findAll(Ocupacion.class);
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
        data.setMensaje("Cargando todas las ocupaciones");
        data.setObjeto(ocupacions);

        return data;
    }

}
