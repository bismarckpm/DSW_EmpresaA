package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ObtenerRecomendacionesComando extends BaseComando {

    public JsonArrayBuilder estudios= Json.createArrayBuilder();
    public long id;

    public ObtenerRecomendacionesComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
        List<Estudio> Lista= dao.findAll(Estudio.class);

        for(Estudio obj: Lista){

            JsonObject estudio = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_estado",obj.get_estado())
                    .add("_estatus",obj.get_estado())
                    .add("_fechaInicio",obj.get_estado())
                    .add("_fechaFin",obj.get_estado())
                    .add("_solicitudEstudio",obj.get_solicitudEstudio().get_id())
                    .add("_usuario",obj.get_estado()).build();

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
