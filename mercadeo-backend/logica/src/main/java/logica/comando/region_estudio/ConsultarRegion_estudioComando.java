package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RegionEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarRegion_estudioComando extends BaseComando {

    public Region_estudioDto region_estudioDto;
    public JsonObject region_estudioJson;
    public long _id;

    public ConsultarRegion_estudioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRegion_estudio dao = new DaoRegion_estudio();
            Region_estudio region_estudio = dao.find(_id,Region_estudio.class);
            this.region_estudioDto= RegionEstudioMapper.mapEntityToDto(region_estudio);

            region_estudioJson= Json.createObjectBuilder()
                    .add("id",region_estudio.get_id())
                    .add("nombre",region_estudio.get_lugar().get_nombre())
                    .add("estado",region_estudio.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Region_estudio consultada")
                .add("region_estudio",region_estudioJson).build();

        return data;
    }
}
