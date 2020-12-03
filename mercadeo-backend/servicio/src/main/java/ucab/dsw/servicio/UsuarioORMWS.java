package ucab.dsw.servicio;

import Implementation.ImpLdap;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import ucab.dsw.Response.UsuarioResponse;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.PersonDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.ExistUserException;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

            LoginDto loginDto = new LoginDto(usuarioDto.getPassword(), usuarioDto.getCorreo());

            if(impLdap.getPerson(loginDto).getEmail().equals(usuarioDto.getCorreo()))
                throw  new ExistUserException("Este usuario ya se encuentra registrado");

            Usuario usuario = setteUsuario(usuarioDto);
            Usuario result = daoUsuario.insert(usuario);
            impLdap.createPerson(result);

            logger.info("Fin del servicio que crea un usuario en el ldap y en la bd ");

            return result;

        }catch (ExistUserException e){

            logger.info("Error en el servicio que crea un usuario en el ldap y en la bd " + e.getMessage());
            throw  new ExistUserException("Este usuario ya se encuentra registrado");

        }

    }

    @POST
    @Path("/autenticar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public UsuarioResponse authenticate(LoginDto loginDto) throws Exception  {

        logger.info("Comienzo del servicio que realiza la autenticación de un usuario");

        try {
            PersonDto personDto = impLdap.getPerson(loginDto);

            if(personDto.getEmail() == null)
                throw new NotAuthorizedException("No tiene autorizacion para acceder al sistema");

            Usuario usuario = daoUsuario.find( Long.parseLong(personDto.getId()), Usuario.class);

            if(usuario.get_password().equals(DigestUtils.md5Hex(loginDto.getPassword())) && loginDto.getEmail().equals(usuario.get_correo()))
                return setterGetUsuario(usuario, usuario.get_id());


            logger.info("Finalización del servicio que realiza la autenticación de un usuario");

        }catch (NotAuthorizedException e){

            logger.info("Error del servicio que realiza la autenticación de un usuario" + e.getMessage());
            throw  new NotAuthorizedException(e);

        }

        throw new NotAuthorizedException("No tiene autorizacion para acceder al sistema");
    }

    @GET
    @Path("/listar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<UsuarioResponse> getAll(@PathParam("id") long idRol) throws Exception {

        logger.info("Comienzo del servicio que obtiene lista de todos los usuarios");

        try {

            List<UsuarioResponse> usuarioResponseList = new ArrayList<>();

            List<Usuario> usuarioList = daoUsuario.findAll(Usuario.class);
            if(idRol != 0){

                usuarioList.stream().filter(i-> (i.get_rol().get_id() == idRol  && i.get_estado().equals("A")) ).
                        collect(Collectors.toList()).stream().forEach(i->{

                            usuarioResponseList.add(setterGetUsuario(i, i.get_id()));

                    });

            }else {

                usuarioList.stream().filter(i->( i.get_estado().equals("A") )).collect(Collectors.toList()).forEach(i -> {
                    usuarioResponseList.add(setterGetUsuario(i, i.get_id()));
                });

            }
            logger.info("Finalización del servicio obtiene lista de todos los usuarios");

            return usuarioResponseList;

        }catch (Exception e){

            throw  new Exception(e);

        }

    }


    @DELETE
    @Path("/eliminar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public UsuarioResponse updateStatus(UsuarioDto usuarioDto) throws Exception {

        Dato_usuarioDto datoUsuarioDto = new Dato_usuarioDto();
        DatoUsuarioORMWS datoUsuarioORMWS = new DatoUsuarioORMWS();

        try {

            logger.info("Comienzo del servicio que realiza una eliminación logica en la Bd y Ldap");

            Usuario usuario = daoUsuario.find(usuarioDto.getId(), Usuario.class);
            usuario.set_estado("I");
            Usuario usuarioUpdate = daoUsuario.update(usuario);

            if(usuarioUpdate.get_datoUsuario() != null ){

                datoUsuarioDto.setId(usuarioUpdate.get_datoUsuario().get_id());

                datoUsuarioORMWS.updateStatus(datoUsuarioDto);
            }

            impLdap.deletePerson(usuario);

            UsuarioResponse result = setterGetUsuario(usuarioUpdate, usuario.get_id());

            logger.info("Fin del servicio que que realiza una eliminación logica  en el Bd y Ldap");

            return result;

        }catch (Exception e){

            throw new Exception(e);

        }

    }


    private UsuarioResponse setterGetUsuario(Usuario usuario, long id){

        UsuarioResponse usuarioResponse = new UsuarioResponse(id, usuario.get_nombreUsuario(), usuario.get_correo(),
                usuario.get_rol().get_id(), usuario.get_estado());

        return usuarioResponse;
    }

    private Usuario setteUsuario(UsuarioDto usuarioDto){

        Rol rol = new Rol(usuarioDto.getRolDto().getId());
        Dato_usuario datoUsuario;

        if(usuarioDto.getDatoUsuarioDto() != null)
            datoUsuario = new Dato_usuario(usuarioDto.getDatoUsuarioDto().getId());
        else
            datoUsuario = null;

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
