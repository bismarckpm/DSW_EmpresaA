package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPregunta_encuestaComando extends BaseComando {

    public long _id;
    public Pregunta_encuesta pregunta_encuesta;

    public EditPregunta_encuestaComando(long _id, Pregunta_encuesta pregunta_encuesta) {
        this._id = _id;
        this.pregunta_encuesta = pregunta_encuesta;
    }

    @Override
    public void execute() {
        try{
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            Pregunta_encuesta resul = dao.update(this.pregunta_encuesta);
            this.pregunta_encuesta=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_encuesta actualizada")
                .add("pregunta_encuesta_enunciado",this.pregunta_encuesta.get_id()).build();

        return data;
    }

}
