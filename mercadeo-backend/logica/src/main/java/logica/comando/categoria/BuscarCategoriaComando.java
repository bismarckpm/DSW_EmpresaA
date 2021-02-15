package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarCategoriaComando extends BaseComando {

    public List<Categoria> categorias = null;

    /**
     * Este comando ejecuta la consulta de todas las categorías
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoCategoria dao= Fabrica.crear(DaoCategoria.class);
            categorias= dao.findAll(Categoria.class);
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
        data.setMensaje("Cargando todas las categorias");
        data.setObjeto(this.categorias);

        return data;
    }

}
