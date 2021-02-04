package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RegionEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRegion_estudioComando extends BaseComando {

    public Region_estudio region_estudio;

    public AddRegion_estudioComando(Region_estudio region_estudio) {
        this.region_estudio = region_estudio;
    }

    @Override
    public void execute() {

        try {
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            Region_estudio resul = dao.insert( this.region_estudio );
            this.region_estudio=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Region_estudio añadida")
                .add("region_estudio_id",this.region_estudio.get_id()).build();

        return data;
    }

}
