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

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject producto = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

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
