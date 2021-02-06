package logica.comando.producto_presentacion_tipo;

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

            JsonObject producto_presentacion_tipo = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_estado",obj.get_estado())
                    .add("_producto",obj.get_producto().get_id())
                    .add("_presentacion",obj.get_presentacion().get_id())
                    .add("_tipo",obj.get_tipo().get_id()).build();

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

