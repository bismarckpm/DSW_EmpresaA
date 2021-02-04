package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditProductoComando extends BaseComando {

    public long _id;
    public Producto producto;

    public EditProductoComando(long _id, Producto producto) {
        this._id = _id;
        this.producto = producto;
    }

    @Override
    public void execute() {
        try{
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            Producto resul = dao.update(this.producto);
            this.producto=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto actualizado")
                .add("producto_nombre",this.producto.get_id()).build();

        return data;
    }

}
