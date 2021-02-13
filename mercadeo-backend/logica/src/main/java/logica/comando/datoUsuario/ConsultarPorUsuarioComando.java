package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ConsultarPorUsuarioComando extends BaseComando {

    public Dato_usuario dato_usuario = null;
    public long id;

    public ConsultarPorUsuarioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException {

        try{
            DaoDato_usuario dao = Fabrica.crear(DaoDato_usuario.class);
            dato_usuario = dao.getPorUsuario(id);
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
        data.setMensaje("Cargando dato usuario");
        data.setObjeto(dato_usuario);

        return data;
    }
}
