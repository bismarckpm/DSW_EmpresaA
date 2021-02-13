package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class BuscarUsuarioRolComando extends BaseComando {

    public List<Usuario> usuarios = null;
    public long _id;

    public BuscarUsuarioRolComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute()throws CustomException {
        try{
            DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);

            if(_id == 0){
                usuarios = daoUsuario.findAll(Usuario.class);
            }else{
                usuarios = daoUsuario.listarUsuarioRol(_id);
            }

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
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.usuarios);
        return data;
    }
}
