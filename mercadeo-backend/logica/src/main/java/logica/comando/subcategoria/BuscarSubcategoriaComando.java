package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarSubcategoriaComando extends BaseComando {

    public List<Subcategoria> subcategorias= null;

    /**
     * Este comando ejecuta la consulta de todas las subcategorías
     */
    @Override
    public void execute()throws CustomException {
        try{
            DaoSubcategoria dao= Fabrica.crear(DaoSubcategoria.class);
            subcategorias= dao.findAll(Subcategoria.class);
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
        data.setMensaje("Cargando todas las subcategorias");
        data.setObjeto(this.subcategorias);

        return data;
    }

}
