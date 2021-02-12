package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddProductoComando extends BaseComando {

    public Producto producto;

    public AddProductoComando(Producto producto) {
        this.producto = producto;
    }

    @Override
    public void execute() throws CustomException {

        try {
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            dao.insert( this.producto );
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
        data.setMensaje("Producto Añadido");
        data.setObjeto(this.producto.get_id());

        return data;
    }

}
