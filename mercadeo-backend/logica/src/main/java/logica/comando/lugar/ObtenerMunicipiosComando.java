package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerMunicipiosComando extends BaseComando {

    public List<Lugar> lugars= null;

    /**
     * Este comando ejecuta la consulta de los lugares de tipo Municipio
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoLugar dao= Fabrica.crear(DaoLugar.class);
            lugars= dao.getMunicipios();
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
        data.setMensaje("Cargando todos los lugares");
        data.setObjeto(this.lugars);

        return data;
    }
}
