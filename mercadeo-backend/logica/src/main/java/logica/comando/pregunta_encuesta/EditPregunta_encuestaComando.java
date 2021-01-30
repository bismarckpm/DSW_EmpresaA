package logica.comando.pregunta_encuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Pregunta_encuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPregunta_encuestaComando extends BaseComando {

    public long _id;
    public Pregunta_encuestaDto pregunta_encuestaDto;

    public EditPregunta_encuestaComando(long _id, Pregunta_encuestaDto pregunta_encuestaDto) {
        this._id = _id;
        this.pregunta_encuestaDto = pregunta_encuestaDto;
    }

    @Override
    public void execute() {
        try{
            DaoPregunta_encuesta dao = Fabrica.crear(DaoPregunta_encuesta.class);
            Pregunta_encuesta pregunta_encuesta= Pregunta_encuestaMapper.mapDtoToEntityUpdate(_id,pregunta_encuestaDto);
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            this.pregunta_encuestaDto=Pregunta_encuestaMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_encuesta actualizada")
                .add("pregunta_encuesta_enunciado",this.pregunta_encuestaDto.getDescripcion()).build();

        return data;
    }

}
