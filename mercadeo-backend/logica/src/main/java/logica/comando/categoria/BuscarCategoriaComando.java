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

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject categoria = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

            categorias.add(categoria);
        }


    }

    @Override
    public JsonObject getResult() {
        ResponseDto responseDto = Fabrica.crear(ResponseDto.class);
        responseDto.mensaje="Cargando todas las categorias";
        responseDto.estado="Éxito";
        responseDto.objeto=this.categorias;

        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las categorias")
                .add("estado","Éxito")
                .add("categorias",categorias).build();

        return data;
    }

}
