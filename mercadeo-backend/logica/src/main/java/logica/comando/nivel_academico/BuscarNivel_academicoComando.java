package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarNivel_academicoComando extends BaseComando {

    public JsonArrayBuilder nivel_academicos= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoNivel_academico dao= Fabrica.crear(DaoNivel_academico.class);
        List<Nivel_academico> Lista= dao.findAll(Nivel_academico.class);

        for(Nivel_academico obj: Lista){

            System.out.print(obj.get_id());
            System.out.print(", ");
            System.out.print(obj.get_nivel());
            System.out.print(", ");
            System.out.print(obj.get_estado());
            System.out.println();

            JsonObject nivel_academico = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nivel())
                    .add("estado",obj.get_estado()).build();

            nivel_academicos.add(nivel_academico);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los nivel_academicos")
                .add("estado","Éxito")
                .add("nivel_academicos",nivel_academicos).build();

        return data;
    }

}
