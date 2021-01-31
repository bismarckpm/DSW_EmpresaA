package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPregunta_encuestaComando extends BaseComando {

    public Pregunta_encuestaDto pregunta_encuestaDto;

    public AddPregunta_encuestaComando(Pregunta_encuestaDto pregunta_encuestaDto) {
        this.pregunta_encuestaDto = pregunta_encuestaDto;
    }

    @Override
    public void execute() {

        try {
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            Pregunta_encuesta pregunta_encuesta = PreguntaEncuestaMapper.mapDtoToEntityInsert(this.pregunta_encuestaDto);
            Pregunta_encuesta resul = dao.insert( pregunta_encuesta );
            this.pregunta_encuestaDto=PreguntaEncuestaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_encuesta añadida")
                .add("pregunta_encuesta_id",this.pregunta_encuestaDto.getId()).build();

        return data;
    }

}
