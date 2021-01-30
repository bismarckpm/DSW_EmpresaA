package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Respuesta_preguntaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRespuesta_preguntaComando extends BaseComando {

    public Respuesta_preguntaDto respuesta_preguntaDto;

    public AddRespuesta_preguntaComando(Respuesta_preguntaDto respuesta_preguntaDto) {
        this.respuesta_preguntaDto = respuesta_preguntaDto;
    }

    @Override
    public void execute() {

        try {
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            Respuesta_pregunta respuesta_pregunta = Respuesta_preguntaMapper.mapDtoToEntityInsert(this.respuesta_preguntaDto);
            Respuesta_pregunta resul = dao.insert( respuesta_pregunta );
            this.respuesta_preguntaDto=Respuesta_preguntaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta_pregunta añadida")
                .add("respuesta_pregunta_id",this.respuesta_preguntaDto.getId()).build();

        return data;
    }

}
