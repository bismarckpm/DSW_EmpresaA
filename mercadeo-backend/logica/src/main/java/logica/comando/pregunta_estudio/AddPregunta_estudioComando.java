package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPregunta_estudioComando extends BaseComando {

    public Pregunta_estudioDto pregunta_estudioDto;

    public AddPregunta_estudioComando(Pregunta_estudioDto pregunta_estudioDto) {
        this.pregunta_estudioDto = pregunta_estudioDto;
    }

    @Override
    public void execute() {

        try {
            DaoPregunta_estudio dao = Fabrica.crear(DaoPregunta_estudio.class);
            Pregunta_estudio pregunta_estudio = PreguntaEstudioMapper.mapDtoToEntityInsert(this.pregunta_estudioDto);
            Pregunta_estudio resul = dao.insert( pregunta_estudio );
            this.pregunta_estudioDto=PreguntaEstudioMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_estudio añadida")
                .add("pregunta_estudio_id",this.pregunta_estudioDto.getId()).build();

        return data;
    }

}
