package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRegion_estudioComando extends BaseComando {
    
    public Region_estudio region_estudio;

    public EditRegion_estudioComando(Region_estudio region_estudio) {
        this.region_estudio = region_estudio;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            dao.update(this.region_estudio);
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
        data.setMensaje("Region de estudio actualizada");
        data.setObjeto(this.region_estudio.get_id());

        return data;
    }

}
