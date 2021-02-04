package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarCategoriaComando extends BaseComando {

    public JsonArrayBuilder categorias= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoCategoria dao= Fabrica.crear(DaoCategoria.class);
        List<Categoria> Lista= dao.findAll(Categoria.class);

        for(Categoria obj: Lista){

            JsonObject categoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

            categorias.add(categoria);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las categorias")
                .add("estado","Éxito")
                .add("categorias",categorias).build();

        return data;
    }

}
