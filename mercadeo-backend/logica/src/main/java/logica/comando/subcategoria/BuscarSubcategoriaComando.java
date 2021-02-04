package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarSubcategoriaComando extends BaseComando {

    public JsonArrayBuilder subcategorias= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoSubcategoria dao= Fabrica.crear(DaoSubcategoria.class);
        List<Subcategoria> Lista= dao.findAll(Subcategoria.class);

        for(Subcategoria obj: Lista){

            JsonObject subcategoria = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_descripcion",obj.get_descripcion())
                    .add("_estado",obj.get_estado())
                    .add("_categoria",obj.get_categoria().get_id()).build();

            subcategorias.add(subcategoria);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las subcategorias")
                .add("estado","Éxito")
                .add("subcategorias",subcategorias).build();

        return data;
    }

}
