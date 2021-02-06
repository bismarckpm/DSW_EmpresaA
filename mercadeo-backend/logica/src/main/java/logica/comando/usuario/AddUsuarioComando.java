package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
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
            dao.insert( this.usuario );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Usuario Añadido");
        data.setObjeto(this.usuario.get_id());

        return data;
    }

}
