package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerEstudiosUsuarioComando extends BaseComando {

    public List<Estudio> estudios = null;
    public long id;

    public ObtenerEstudiosUsuarioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{

        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            estudios = dao.getEstudiosUsuario(id);
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
        data.setMensaje("Cargando estudios del cliente");
        data.setObjeto(this.estudios);

        return data;
    }
}
