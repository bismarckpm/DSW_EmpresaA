package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRespuestaComando extends BaseComando {

    public RespuestaDto respuestaDto;

    public AddRespuestaComando(RespuestaDto respuestaDto) {
        this.respuestaDto = respuestaDto;
    }

    @Override
    public void execute() {

        try {
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            Respuesta respuesta = RespuestaMapper.mapDtoToEntityInsert(this.respuestaDto);
            Respuesta resul = dao.insert( respuesta );
            this.respuestaDto=RespuestaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta añadida")
                .add("respuesta_id",this.respuestaDto.getId()).build();

        return data;
    }

}
