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

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject subcategoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

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
