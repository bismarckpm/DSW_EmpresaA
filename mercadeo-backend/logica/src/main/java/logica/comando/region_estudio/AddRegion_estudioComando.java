package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RegionEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;
import java.util.List;

public class AddRegion_estudioComando extends BaseComando {

    public List<Region_estudio> region_estudio;

    public AddRegion_estudioComando(List<Region_estudio> region_estudio) {
        this.region_estudio = region_estudio;
    }

    @Override
    public void execute() throws CustomException{
        try {
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            for (Region_estudio region_estudiox : region_estudio) {
                dao.insert(region_estudiox);
            }
        }catch ( CustomException ex ) {
            throw ex;
        } catch (Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Region_estudios Añadidos");
        data.setObjeto(this.region_estudio);

        return data;
    }
}
