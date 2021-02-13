package logica.comando.usuario;

import Implementation.ImpLdap;
import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import org.apache.commons.codec.digest.DigestUtils;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.PersonDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Response.UsuarioResponse;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import javax.ws.rs.NotAuthorizedException;
import java.util.List;

public class AutenticarComando extends BaseComando {

    public Usuario usuario;
    public LoginDto loginDto;
    public UsuarioResponse usuarioResponse;
    private ImpLdap impLdap = new ImpLdap();

    public AutenticarComando(LoginDto loginDto){
        this.loginDto=loginDto;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoUsuario daoUsuario = Fabrica.crear(DaoUsuario.class);
            PersonDto personDto = impLdap.getPerson(loginDto);

            if(personDto.getEmail() == null)
                throw new CustomException("011","No tiene autorizacion para acceder al sistema");

            usuario = daoUsuario.find( Long.parseLong(personDto.getId()), Usuario.class);

            if(usuario.get_password().equals(DigestUtils.md5Hex(loginDto.getPassword())) && loginDto.getEmail().equals(usuario.get_correo())){
                usuarioResponse = new UsuarioResponse(usuario.get_id(), usuario.get_nombreUsuario(), usuario.get_correo(),
                        usuario.get_rol().get_id(), usuario.get_estado());
            }

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.usuarioResponse);
        return data;
    }
}
