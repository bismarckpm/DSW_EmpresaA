package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Region_estudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRegion_estudioComando extends BaseComando {

    public long _id;
    public Region_estudioDto region_estudioDto;

    public EditRegion_estudioComando(long _id, Region_estudioDto region_estudioDto) {
        this._id = _id;
        this.region_estudioDto = region_estudioDto;
    }

    @Override
    public void execute() {
        try{
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            Region_estudio region_estudio= Region_estudioMapper.mapDtoToEntityUpdate(_id,region_estudioDto);
            Region_estudio resul = dao.update(region_estudio);
            this.region_estudioDto=Region_estudioMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Region_estudio actualizada")
                .add("region_estudio_nombre",this.region_estudioDto.getLugarDto().getNombre()).build();

        return data;
    }

}
