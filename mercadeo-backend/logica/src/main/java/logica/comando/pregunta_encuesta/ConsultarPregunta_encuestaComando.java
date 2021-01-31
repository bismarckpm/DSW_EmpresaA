package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarPregunta_encuestaComando extends BaseComando {

    public Pregunta_encuestaDto pregunta_encuestaDto;
    public JsonObject pregunta_encuestaJson;
    public long _id;

    public ConsultarPregunta_encuestaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = dao.find(_id,Pregunta_encuesta.class);
            this.pregunta_encuestaDto= PreguntaEncuestaMapper.mapEntityToDto(pregunta_encuesta);

            pregunta_encuestaJson= Json.createObjectBuilder()
                    .add("id",pregunta_encuesta.get_id())
                    .add("nombre",pregunta_encuesta.get_descripcion())
                    .add("estado",pregunta_encuesta.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_encuesta consultada")
                .add("pregunta_encuesta",pregunta_encuestaJson).build();

        return data;
    }
}
