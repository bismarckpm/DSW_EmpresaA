package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ObtenerEstudiosRespondidos2Comando extends BaseComando {

    public List<Estudio> estudios = null;
    public long id;

    public ObtenerEstudiosRespondidos2Comando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException {

        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            estudios = dao.getEstudiosRespondidosCompletos(id);
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
        data.setMensaje("Cargando estudios respondidos");
        data.setObjeto(estudios);

        return data;
    }
}
