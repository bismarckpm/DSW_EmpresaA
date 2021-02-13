package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class EditRegion_estudioComando extends BaseComando {

    public List<Region_estudio> region_estudio;
    public long id;

    public EditRegion_estudioComando(List<Region_estudio> region_estudio) {
        this.region_estudio = region_estudio;
        this.id = id;
    }

    @Override
    public void execute()throws CustomException {
        try{
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            List<Region_estudio> regionesOld = dao.getRegionesActualizar(id);
            for (Region_estudio regAux : regionesOld) {
                dao.delete (regAux );
            }
            for (Region_estudio region_estudiox : region_estudio) {
                dao.update(region_estudiox);
            }
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Region_estudios actualizados");
        data.setObjeto(this.region_estudio);

        return data;
    }
}
