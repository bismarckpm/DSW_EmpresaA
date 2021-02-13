package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import org.apache.commons.codec.digest.DigestUtils;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;

import java.util.List;

public class CambiarContraseñaComando extends BaseComando {

    public Usuario usuario;
    public long _id;
    public String clave;

    public CambiarContraseñaComando(long _id, String clave){
        this._id=_id;
        this.clave=clave;
    }

    @Override
    public void execute() {
        try{
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            usuario = dao.find(_id, Usuario.class);
            usuario.set_password(DigestUtils.md5Hex(clave));
            dao.update( usuario );
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.usuario);
        return data;
    }
}
