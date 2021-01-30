package logica.comando.respuesta_pregunta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Respuesta_preguntaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRespuesta_preguntaComando extends BaseComando {

    public long _id;
    public Respuesta_preguntaDto respuesta_preguntaDto;

    public EditRespuesta_preguntaComando(long _id, Respuesta_preguntaDto respuesta_preguntaDto) {
        this._id = _id;
        this.respuesta_preguntaDto = respuesta_preguntaDto;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta_pregunta dao = Fabrica.crear(DaoRespuesta_pregunta.class);
            Respuesta_pregunta respuesta_pregunta= Respuesta_preguntaMapper.mapDtoToEntityUpdate(_id,respuesta_preguntaDto);
            Respuesta_pregunta resul = dao.update(respuesta_pregunta);
            this.respuesta_preguntaDto=Respuesta_preguntaMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta_pregunta actualizada")
                .add("respuesta_pregunta_nombre",this.respuesta_preguntaDto.getNombre()).build();

        return data;
    }

}
