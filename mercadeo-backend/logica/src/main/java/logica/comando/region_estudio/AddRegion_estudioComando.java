package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RegionEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRegion_estudioComando extends BaseComando {

    public Region_estudioDto region_estudioDto;

    public AddRegion_estudioComando(Region_estudioDto region_estudioDto) {
        this.region_estudioDto = region_estudioDto;
    }

    @Override
    public void execute() {

        try {
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            Region_estudio region_estudio = RegionEstudioMapper.mapDtoToEntityInsert(this.region_estudioDto);
            Region_estudio resul = dao.insert( region_estudio );
            this.region_estudioDto=RegionEstudioMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Region_estudio añadida")
                .add("region_estudio_id",this.region_estudioDto.getId()).build();

        return data;
    }

}
