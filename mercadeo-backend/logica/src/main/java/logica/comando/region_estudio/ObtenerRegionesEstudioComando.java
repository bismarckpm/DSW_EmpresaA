package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerRegionesEstudioComando extends BaseComando {

    public List<Lugar> lugares = null;
    public long id;

    public ObtenerRegionesEstudioComando(long id) {
        this.id = id;
    }

    /**
     * Este comando ejecuta la consulta de las regiones de una solicitud de estudio
     */
    @Override
    public void execute() throws CustomException{

        try{
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            lugares = dao.getRegionesDeSolicitud(id);
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
        data.setMensaje("Cargando lugares de un estudio");
        data.setObjeto(this.lugares);

        return data;
    }
}
