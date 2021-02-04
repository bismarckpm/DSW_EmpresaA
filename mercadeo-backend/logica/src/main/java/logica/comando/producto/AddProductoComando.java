package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
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
    public void execute() {

        try {
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            Producto resul = dao.insert( this.producto );
            this.producto=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto añadido")
                .add("producto_id",this.producto.get_id()).build();

        return data;
    }

}
