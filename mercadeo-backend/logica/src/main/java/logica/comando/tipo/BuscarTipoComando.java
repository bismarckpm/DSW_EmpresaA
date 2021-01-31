package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarTipoComando extends BaseComando {

    public JsonArrayBuilder tipos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoTipo dao= Fabrica.crear(DaoTipo.class);
        List<Tipo> Lista= dao.findAll(Tipo.class);

        for(Tipo obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nombre());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject tipo = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

            tipos.add(tipo);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los tipos")
                .add("estado","Éxito")
                .add("tipos",tipos).build();

        return data;
    }

}
