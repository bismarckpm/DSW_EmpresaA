package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarProductoComando extends BaseComando {

    public List<Producto> productos= null;

    @Override
    public void execute() throws CustomException{
        try{
            DaoProducto dao= Fabrica.crear(DaoProducto.class);
            productos= dao.findAll(Producto.class);
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
        data.setMensaje("Cargando todos los productos");
        data.setObjeto(this.productos);

        return data;
    }

}
