package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.RegionEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarRegion_estudioComando extends BaseComando {

    public Region_estudio region_estudio;
    public long _id;

    public ConsultarRegion_estudioComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de una región de estudio
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoRegion_estudio dao = new DaoRegion_estudio();
            this.region_estudio = dao.find(_id, Region_estudio.class);

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Region de estudio consultada");
        data.setObjeto(this.region_estudio);

        return data;
    }
}
