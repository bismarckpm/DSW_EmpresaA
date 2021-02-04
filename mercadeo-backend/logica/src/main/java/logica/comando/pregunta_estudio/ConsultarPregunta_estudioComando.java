package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarPregunta_estudioComando extends BaseComando {

    public Pregunta_estudioDto pregunta_estudioDto;
    public JsonObject pregunta_estudioJson;
    public long _id;

    public ConsultarPregunta_estudioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            Pregunta_estudio pregunta_estudio = dao.find(_id,Pregunta_estudio.class);
            this.pregunta_estudioDto= PreguntaEstudioMapper.mapEntityToDto(pregunta_estudio);

            pregunta_estudioJson= Json.createObjectBuilder()
                    .add("_id",pregunta_estudio.get_id())
                    .add("_pregunta",pregunta_estudio.get_pregunta())
                    .add("_estado",pregunta_estudio.get_estado())
                    .add("_estudio",pregunta_estudio.get_estudio().get_id())
                    .add("_preguntaEncuesta",pregunta_estudio.get_preguntaEncuesta().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Pregunta_estudio consultada")
                .add("pregunta_estudio",pregunta_estudioJson).build();

        return data;
    }
}
