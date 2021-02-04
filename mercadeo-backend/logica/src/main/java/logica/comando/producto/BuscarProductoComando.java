package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarProductoComando extends BaseComando {

    public JsonArrayBuilder productos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoProducto dao= Fabrica.crear(DaoProducto.class);
        List<Producto> Lista= dao.findAll(Producto.class);

        for(Producto obj: Lista){

            JsonObject producto = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_descripcion",obj.get_descripcion())
                    .add("_estado",obj.get_estado())
                    .add("_marca",obj.get_marca().get_id())
                    .add("_subcategoria",obj.get_subcategoria().get_id())
                    .add("_usuario",obj.get_usuario().get_id()).build();

            productos.add(producto);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos las productos")
                .add("estado","Éxito")
                .add("productos",productos).build();

        return data;
    }

}
