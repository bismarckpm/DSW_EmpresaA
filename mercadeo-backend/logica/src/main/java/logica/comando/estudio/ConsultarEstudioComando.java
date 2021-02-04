package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.EstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConsultarEstudioComando extends BaseComando {

    public EstudioDto estudioDto;
    public JsonObject estudioJson;
    public long _id;

    public ConsultarEstudioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(_id,Estudio.class);
            this.estudioDto= EstudioMapper.mapEntityToDto(estudio);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            estudioJson= Json.createObjectBuilder()
                    .add("_id",estudio.get_id())
                    .add("_nombre",estudio.get_nombre())
                    .add("_fechaInicio",dateFormat.format(estudio.get_fechaInicio()))
                    .add("_fechaFin",dateFormat.format(estudio.get_fechaFin()))
                    .add("_estatus",estudio.get_estatus())
                    .add("_estado",estudio.get_estado())
                    .add("_solicitudEstudio",estudio.get_solicitudEstudio().get_id())
                    .add("_usuario",estudio.get_usuario().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Estudio consultado")
                .add("estudio",estudioJson).build();

        return data;
    }
}
