package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPoblacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerPoblacionEstudioComando extends BaseComando {

    public List<Usuario> poblacion = null;
    public long id;

    public ObtenerPoblacionEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{

        try{
            DaoPoblacion dao= Fabrica.crear(DaoPoblacion.class);
            poblacion = dao.listarPoblacionEstudioUsers(id);
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
        data.setMensaje("Cargando poblacion de estudio");
        data.setObjeto(this.poblacion);

        return data;
    }
}
