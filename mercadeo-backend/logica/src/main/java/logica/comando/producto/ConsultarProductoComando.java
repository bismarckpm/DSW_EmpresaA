package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarProductoComando extends BaseComando {

    public ProductoDto productoDto;
    public JsonObject productoJson;
    public long _id;

    public ConsultarProductoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoProducto dao = new DaoProducto();
            Producto producto = dao.find(_id,Producto.class);
            this.productoDto= ProductoMapper.mapEntityToDto(producto);

            productoJson= Json.createObjectBuilder()
                    .add("_id",producto.get_id())
                    .add("_nombre",producto.get_nombre())
                    .add("_descripcion",producto.get_descripcion())
                    .add("_estado",producto.get_estado())
                    .add("_marca",producto.get_marca().get_id())
                    .add("_subcategoria",producto.get_subcategoria().get_id())
                    .add("_usuario",producto.get_usuario().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto consultado")
                .add("producto",productoJson).build();

        return data;
    }
}
