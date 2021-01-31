package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaMapper;

import javax.json.JsonObject;
import javax.json.Json;
import java.util.ArrayList;
import java.util.List;

public class AddRespuestaComando extends BaseComando {

    public List<RespuestaDto> respuestaDto;

    public AddRespuestaComando(List<RespuestaDto> respuestaDto) {
        this.respuestaDto = respuestaDto;
    }

    @Override
    public void execute() {

        try {
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            List<Respuesta> respuesta = RespuestaMapper.mapDtoToEntityInsert(this.respuestaDto);
            List<Respuesta> resul = new ArrayList<>();
            for (Respuesta respuestax : respuesta) {
                resul.add(dao.insert(respuestax));
            }
            this.respuestaDto=RespuestaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        String salida = "";
        for(RespuestaDto rdto : respuestaDto){
            salida= salida + rdto.getId() + " - ";
        }
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Respuesta añadida")
                .add("respuesta_id",salida).build();

        return data;
    }

}
