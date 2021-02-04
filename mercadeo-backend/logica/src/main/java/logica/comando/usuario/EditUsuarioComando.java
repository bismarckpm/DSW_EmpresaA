package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonObject;

public class EditUsuarioComando extends BaseComando {

    public long _id;
    public Usuario usuario;

    public EditUsuarioComando(long _id, Usuario usuario) {
        this._id = _id;
        this.usuario = usuario;
    }

    @Override
    public void execute() {
        try{
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            Usuario resul = dao.update(usuario);
            this.usuario=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Usuario actualizado")
                .add("usuario_nombre",this.usuario.get_id()).build();

        return data;
    }

}
