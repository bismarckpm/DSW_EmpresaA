package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPregunta_encuestaComando extends BaseComando {

    public Pregunta_encuesta pregunta_encuesta;

    public AddPregunta_encuestaComando(Pregunta_encuesta pregunta_encuesta) {
        this.pregunta_encuesta = pregunta_encuesta;
    }

    @Override
    public void execute() {

        try {
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            Pregunta_encuesta resul = dao.insert( this.pregunta_encuesta );
            this.pregunta_encuesta=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_encuesta añadida")
                .add("pregunta_encuesta_id",this.pregunta_encuesta.get_id()).build();

        return data;
    }

}
