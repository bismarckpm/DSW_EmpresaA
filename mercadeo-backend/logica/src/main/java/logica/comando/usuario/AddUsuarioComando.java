package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Usuario;
import Implementation.ImpLdap;

public class AddUsuarioComando extends BaseComando {

    public Usuario usuario;
    private ImpLdap impLdap = new ImpLdap();

    public AddUsuarioComando(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void execute() {

        try {
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            dao.insert( this.usuario );
            impLdap.createPerson(usuario);
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Usuario Añadido");
        data.setObjeto(this.usuario);

        return data;
    }

}
