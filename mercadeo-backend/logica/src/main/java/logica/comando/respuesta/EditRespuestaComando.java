package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRespuestaComando extends BaseComando {

    public long _id;
    public RespuestaDto respuestaDto;

    public EditRespuestaComando(long _id, RespuestaDto respuestaDto) {
        this._id = _id;
        this.respuestaDto = respuestaDto;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            Respuesta respuesta= RespuestaMapper.mapDtoToEntityUpdate(_id,respuestaDto);
            Respuesta resul = dao.update(respuesta);
            this.respuestaDto=RespuestaMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta actualizada")
                .add("respuesta_id",this.respuestaDto.getId()).build();

        return data;
    }

}
