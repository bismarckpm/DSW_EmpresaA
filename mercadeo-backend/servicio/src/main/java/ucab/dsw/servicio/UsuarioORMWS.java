package ucab.dsw.servicio;

import Implementation.ImpLdap;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.PersonDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Usuario;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Log
@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioORMWS {

    private Logger logger = Logger.getLogger(UsuarioORMWS.class.getName());

    private DaoUsuario daoUsuario = new DaoUsuario();
    private DaoRol daoRol = new DaoRol();
    private ImpLdap impLdap = new ImpLdap();

    @POST
    @Path("/crear")
    public Usuario create(UsuarioDto usuarioDto) throws Exception {
        try {

            logger.info("Comienzo del servicio que crea un usuario en el ldap y en la bd ");

            Usuario usuario = setteUsuario(usuarioDto);
            Usuario result = daoUsuario.insert(usuario);
            impLdap.createPerson(result);

            logger.info("Fin del servicio que crea un usuario en el ldap y en la bd ");

            return result;

        }catch (Exception e){

            throw new Exception(e);

        }

    }

    @GET
    @Path("/eliminar")
    public Usuario updateStatus(UsuarioDto usuarioDto) throws Exception {
        try {

            logger.info("Comienzo del servicio que realiza una eliminación logica en la Bd y Ldap");

            Usuario usuario = daoUsuario.find(usuarioDto.getId(), Usuario.class);
            usuario.set_estado("I");
            Usuario usuarioUpdate = daoUsuario.update(usuario);
            impLdap.deletePerson(usuario);

            logger.info("Fin del servicio que que realiza una eliminación logica  en el Bd y Ldap");

            return usuarioUpdate;

        }catch (Exception e){

            throw new Exception(e);

        }

    }

    @POST
    @Path("/autenticar")
    public Usuario authenticate(LoginDto loginDto) throws Exception {

        logger.info("Comienzo del servicio que realiza la autenticación de un usuario");

        try {
            PersonDto personDto = impLdap.getPerson(loginDto);

            if(personDto == null)
                return null ;

            Usuario usuario = daoUsuario.find( Long.parseLong(personDto.getId()), Usuario.class);

            if(usuario.get_password().equals(DigestUtils.md5Hex(loginDto.getPassword())))
                return usuario;


            logger.info("Finalización del servicio que realiza la autenticación de un usuario");

        }catch (Exception e){

            throw  new Exception(e);

        }

        return null;
    }

    @POST
    @Path("/autenticar")
    public List<Usuario> getAll() throws Exception {

        logger.info("Comienzo del servicio que obtiene lista de todos los usuarios");

        try {

            List<Usuario> usuarioList = daoUsuario.findAll(Usuario.class);

            logger.info("Finalización del servicio obtiene lista de todos los usuarios");

            return usuarioList;

        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    private Usuario setteUsuario(UsuarioDto usuarioDto){

        Rol rol = new Rol(usuarioDto.getRolDto().getId());
        Dato_usuario datoUsuario = new Dato_usuario(usuarioDto.getDatoUsuarioDto().getId());

        Usuario usuario = new Usuario();

        usuario.set_correo(usuarioDto.getCorreo());
        usuario.set_password(DigestUtils.md5Hex(usuarioDto.getPassword()));
        usuario.set_estado("A");
        usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
        usuario.set_datoUsuario(datoUsuario);
        usuario.set_rol(rol);

        return usuario;

    }
}
