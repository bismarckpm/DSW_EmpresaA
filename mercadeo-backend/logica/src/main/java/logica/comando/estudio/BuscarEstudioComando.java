package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarEstudioComando extends BaseComando {

    public JsonArrayBuilder estudios= Json.createArrayBuilder();

    public BuscarEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
        List<Estudio> Lista= dao.findAll(Estudio.class);

        DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
        List<Estudio> estudios = dao.getEstudiosCliente(id);

        for(Estudio obj: Lista){

            JsonObject estudio = Json.createObjectBuilder().add("id",obj.get_id())
                    .add("nombre",obj.get_nombre())
                    .add("estado",obj.get_estado()).build();

            estudios.add(estudio);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todos los estudios")
                .add("estado","Éxito")
                .add("estudios",estudios).build();

        return data;
    }

}
