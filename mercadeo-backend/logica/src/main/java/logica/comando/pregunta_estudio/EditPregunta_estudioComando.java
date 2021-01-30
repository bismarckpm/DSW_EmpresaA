package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Pregunta_estudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPregunta_estudioComando extends BaseComando {

    public long _id;
    public Pregunta_estudioDto pregunta_estudioDto;

    public EditPregunta_estudioComando(long _id, Pregunta_estudioDto pregunta_estudioDto) {
        this._id = _id;
        this.pregunta_estudioDto = pregunta_estudioDto;
    }

    @Override
    public void execute() {
        try{
            DaoPregunta_estudio dao = Fabrica.crear(DaoPregunta_estudio.class);
            Pregunta_estudio pregunta_estudio= Pregunta_estudioMapper.mapDtoToEntityUpdate(_id,pregunta_estudioDto);
            Pregunta_estudio resul = dao.update(pregunta_estudio);
            this.pregunta_estudioDto=Pregunta_estudioMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_estudio actualizada")
                .add("pregunta_estudio_enunciado",this.pregunta_estudioDto.getPregunta()).build();

        return data;
    }

}
