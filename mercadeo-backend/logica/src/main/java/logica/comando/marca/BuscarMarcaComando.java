package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarMarcaComando extends BaseComando {

    public List<Marca> marcas= null;

    @Override
    public void execute() throws CustomException {
        try{
            DaoMarca dao= Fabrica.crear(DaoMarca.class);
            marcas= dao.findAll(Marca.class);
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
        data.setMensaje("Cargando todas las marcas");
        data.setObjeto(marcas);

        return data;
    }

}
