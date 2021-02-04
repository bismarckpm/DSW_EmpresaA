package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPregunta_estudioComando extends BaseComando {

    public Pregunta_estudio pregunta_estudio;

    public AddPregunta_estudioComando(Pregunta_estudio pregunta_estudio) {
        this.pregunta_estudio = pregunta_estudio;
    }

    @Override
    public void execute() {

        try {
            DaoPregunta_estudio dao = Fabrica.crear(DaoPregunta_estudio.class);
            Pregunta_estudio resul = dao.insert( this.pregunta_estudio );
            this.pregunta_estudio=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_estudio añadida")
                .add("pregunta_estudio_id",this.pregunta_estudio.get_id()).build();

        return data;
    }

}
