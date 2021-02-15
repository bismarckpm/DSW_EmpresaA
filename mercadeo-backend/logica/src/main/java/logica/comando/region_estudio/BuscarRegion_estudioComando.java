package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRegion_estudioComando extends BaseComando {

    public List<Region_estudio> region_estudios= null;

    @Override
    public void execute() throws CustomException{
        try{
            DaoRegion_estudio dao= Fabrica.crear(DaoRegion_estudio.class);
            region_estudios= dao.findAll(Region_estudio.class);
        }catch ( CustomException ex ) {
            throw ex;
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las regiones de estudio");
        data.setObjeto(this.region_estudios);

        return data;
    }

}
