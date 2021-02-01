import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.entidades.Producto_presentacion_tipo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarProducto_presentacion_tipoComando extends BaseComando {

    public JsonArrayBuilder producto_presentacion_tipos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoProducto_presentacion_tipo dao= Fabrica.crear(DaoProducto_presentacion_tipo.class);
        List<Producto_presentacion_tipo> Lista= dao.findAll(Producto_presentacion_tipo.class);

        for(Producto_presentacion_tipo obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_producto().get_nombre() + " " + obj.get_presentacion().get_titulo() + " " + obj.get_tipo().get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject producto_presentacion_tipo = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_producto().get_nombre() + " " + obj.get_presentacion().get_titulo() + " " + obj.get_tipo().get_nombre())
                    .add("estado",obj.get_estado()).build();

            producto_presentacion_tipos.add(producto_presentacion_tipo);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los producto_presentacion_tipos")
                .add("estado","Éxito")
                .add("producto_presentacion_tipos",producto_presentacion_tipos).build();

        return data;
    }

}

