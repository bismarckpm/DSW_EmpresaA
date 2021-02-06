package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_economico;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarNivel_economicoComando extends BaseComando {

    public JsonArrayBuilder nivel_economicos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoNivel_economico dao= Fabrica.crear(DaoNivel_economico.class);
        List<Nivel_economico> Lista= dao.findAll(Nivel_economico.class);

        for(Nivel_economico obj: Lista){

            JsonObject nivel_economico = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nivel",obj.get_nivel())
                    .add("_estado",obj.get_estado()).build();

            nivel_economicos.add(nivel_economico);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los nivel_economicos")
                .add("estado","Éxito")
                .add("nivel_economicos",nivel_economicos).build();

        return data;
    }

}
