package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerEstadosComando extends BaseComando {

    public List<Lugar> lugars= null;

    @Override
    public void execute() throws CustomException{
        try{
            DaoLugar dao= Fabrica.crear(DaoLugar.class);
            lugars= dao.getEstados();
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
        data.setMensaje("Cargando todos los Estados");
        data.setObjeto(this.lugars);

        return data;
    }
}
