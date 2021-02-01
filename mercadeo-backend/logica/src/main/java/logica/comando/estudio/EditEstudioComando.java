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

public class EditEstudioComando extends BaseComando {

    public long _id;
    public EstudioDto estudioDto;

    public EditEstudioComando(long _id, EstudioDto estudioDto) {
        this._id = _id;
        this.estudioDto = estudioDto;
    }

    @Override
    public void execute() {
        try{
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            Estudio estudio= EstudioMapper.mapDtoToEntityUpdate(_id,estudioDto);
            Estudio resul = dao.update(estudio);
            this.estudioDto=EstudioMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Estudio actualizado")
                .add("estudio_nombre",this.estudioDto.getNombre()).build();

        return data;
    }

}
