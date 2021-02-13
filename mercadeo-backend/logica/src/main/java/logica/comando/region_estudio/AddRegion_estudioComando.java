package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;
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
    public void execute() throws CustomException {

        try {
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            dao.insert( this.region_estudio );
        }catch ( CustomException ex ) {
            throw ex;
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Region_estudio Añadida");
        data.setObjeto(this.region_estudio.get_id());

        return data;
    }

}
