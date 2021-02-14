package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditProductoComando extends BaseComando {
    
    public Producto producto;

    public EditProductoComando(Producto producto) {
        this.producto = producto;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            dao.update(this.producto);
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
        data.setMensaje("Producto actualizado");
        data.setObjeto(this.producto);

        return data;
    }

}
