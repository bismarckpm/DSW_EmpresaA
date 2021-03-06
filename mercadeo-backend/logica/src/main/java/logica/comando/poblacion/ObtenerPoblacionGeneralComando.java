package logica.comando.poblacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPoblacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerPoblacionGeneralComando extends BaseComando {

    public List<Usuario> poblacion = null;
    public long id;

    public ObtenerPoblacionGeneralComando(long id) {
        this.id = id;
    }

    @Override
    public void execute()throws CustomException {

        try{
            DaoPoblacion dao= Fabrica.crear(DaoPoblacion.class);
            poblacion = dao.listarPoblacionGeneral(id);
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
        data.setMensaje("Cargando poblacions del usuario");
        data.setObjeto(this.poblacion);

        return data;
    }
}
