package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.UsuarioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddUsuarioComando extends BaseComando {

    public Usuario usuario;

    public AddUsuarioComando(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void execute() {

        try {
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            Usuario resul = dao.insert( this.usuario );
            this.usuario=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Usuario añadido")
                .add("usuario_id",this.usuario.get_id()).build();

        return data;
    }

}
