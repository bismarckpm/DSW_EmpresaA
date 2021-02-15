package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.UsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarUsuarioComando extends BaseComando {

    public Usuario usuario;
    public long _id;

    public ConsultarUsuarioComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de un usuario específico
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoUsuario dao = new DaoUsuario();
            this.usuario = dao.find(_id, Usuario.class);

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
        data.setMensaje("Usuario consultado");
        data.setObjeto(this.usuario);

        return data;
    }
}
