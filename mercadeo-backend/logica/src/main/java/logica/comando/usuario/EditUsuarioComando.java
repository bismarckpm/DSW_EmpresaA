package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonObject;

public class EditUsuarioComando extends BaseComando {
    
    public Usuario usuario;

    public EditUsuarioComando(long _id, Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void execute() {
        try{
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            dao.update(this.usuario);
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Usuario actualizado");
        data.setObjeto(this.usuario.get_id());

        return data;
    }

}
