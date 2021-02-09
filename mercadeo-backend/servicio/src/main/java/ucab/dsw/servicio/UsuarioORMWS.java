package ucab.dsw.servicio;

import Implementation.ImpLdap;
import logica.comando.usuario.ConsultarUsuarioComando;
import logica.comando.usuario.EditUsuarioComando;
import logica.fabrica.Fabrica;
import org.apache.commons.codec.digest.DigestUtils;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.ListaEncuestasE;
import ucab.dsw.Response.Respuesta_preguntaResponse;
import ucab.dsw.Response.UsuarioResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.ExistUserException;
import ucab.dsw.mappers.UsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioORMWS {

    private DaoUsuario daoUsuario = new DaoUsuario();
    private ImpLdap impLdap = new ImpLdap();

    private static Logger logger = LoggerFactory.getLogger(UsuarioORMWS.class);


    /**
     * Este método registra en el sistema un nuevo usuario
     *
     * @param  "UsuarioDto"  usuario a ser registrada
     * @return      la UsuarioDto que ha sido registrada en el sistema
     */
    @POST
    @Path("/crear")
    public Usuario create(UsuarioDto usuarioDto) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un usuario");
        try {

            LoginDto loginDto = new LoginDto(usuarioDto.getPassword(), usuarioDto.getCorreo());

            /*if(impLdap.getPerson(loginDto).getEmail().equals(usuarioDto.getCorreo()))
                throw  new ExistUserException("Este usuario ya se encuentra registrado");*/

            Usuario usuario = setteUsuario(usuarioDto);
            Usuario result = daoUsuario.insert(usuario);

            impLdap.createPerson(result);
            logger.debug("Saliendo del método que agrega un usuario");
            return result;

        }catch (Exception e){

            throw  new ExistUserException("Este usuario ya se encuentra registrado");

        }

    }

    /**
     * Este método actualiza un usuario específico
     *
     * @param  usuarioDto  usuario a ser actualizado
     * @param  id  id del usuario a ser actualizado
     * @return      el usuarioDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateUsuario/{id}" )
    public Response updateUsuario(@PathParam("id") long id , UsuarioDto usuarioDto) throws Exception
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un usuario");
        JsonObject resultado;
        try
        {
            EditUsuarioComando comando= Fabrica.crearComandoConEntidad(EditUsuarioComando.class, UsuarioMapper.mapDtoToEntityUpdate(usuarioDto.getId(),usuarioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método autentica en el sistema en el sistema la informacion del usuario
     *
     * @param  "LoginDto"  usuario a autenticar
     * @return      la UsuarioResponse que ha sido autenticado en el sistema
     */
    @POST
    @Path("/autenticar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public UsuarioResponse authenticate(LoginDto loginDto) throws Exception  {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que autentica un usuario");

        try {
            PersonDto personDto = impLdap.getPerson(loginDto);

            if(personDto.getEmail() == null)
                throw new NotAuthorizedException("No tiene autorizacion para acceder al sistema");

            Usuario usuario = daoUsuario.find( Long.parseLong(personDto.getId()), Usuario.class);

            if(usuario.get_password().equals(DigestUtils.md5Hex(loginDto.getPassword())) && loginDto.getEmail().equals(usuario.get_correo()))
                return setterGetUsuario(usuario, usuario.get_id());


        }catch (Exception e){

            throw  new Exception(e);

        }
        logger.debug("Saliendo del método que autentica un usuario");
        throw new Exception("No tiene autorizacion para acceder al sistema");
    }

    /**
     * Este método obtiene la información de una lista de telefonos de un usuario especifico
     *
     * @param  "id"  id usuario al cual se le buscaran los telefonos
     * @return      la lista de telefonos a obtener
     */
    @GET
    @Path("/listar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<UsuarioResponse> getAll(@PathParam("id") long idRol) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los usuarios");

        try {

            List<UsuarioResponse> usuarioResponseList = new ArrayList<>();

            List<Usuario> usuarioList = daoUsuario.findAll(Usuario.class);
            if(idRol != 0){

                usuarioList.stream().filter(i-> (i.get_rol().get_id() == idRol  && i.get_estado().equals("A")) ).
                        collect(Collectors.toList()).stream().forEach(i->{

                            usuarioResponseList.add(setterGetUsuario(i, i.get_id()));

                    });
                logger.debug("Saliendo del método que consulta todos los usuarios");
            }else {

                usuarioList.stream().filter(i->( i.get_estado().equals("A") )).collect(Collectors.toList()).forEach(i -> {
                    usuarioResponseList.add(setterGetUsuario(i, i.get_id()));
                });

            }

            return usuarioResponseList;

        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    /**
     * Este método obtiene del sistema un usuario
     *
     * @param  "Usuario"  usuario a ser setteado
     * @param  "id"  id del usuario usuario a ser setteado
     * @return      el UsuarioResponse que ha sido setteado en el sistema
     */
    private UsuarioResponse setterGetUsuario(Usuario usuario, long id){

        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un usuario");

        UsuarioResponse usuarioResponse = new UsuarioResponse(id, usuario.get_nombreUsuario(), usuario.get_correo(),
                usuario.get_rol().get_id(), usuario.get_estado());
        logger.debug("Saliendo del método que actualiza un usuario");
        return usuarioResponse;
    }

    /**
     * Este método settea en el sistema un nuevo usuario
     *
     * @param  "UsuarioDto"  usuario a ser setteado
     * @return      el Usuario que ha sido setteado en el sistema
     */
    private Usuario setteUsuario(UsuarioDto usuarioDto){

        BasicConfigurator.configure();
        logger.debug("Entrando al método que obtiene y actualiza un usuario");

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
        logger.debug("Saliendo del método que obtiene y actualiza un usuario");
        return usuario;

    }

    /**
     * Este método retorna los estudios que están disponibles para un encuestado
     *
     * @param  "id"  es el id del dato_usuario que tiene asociado el usuario
     * @return      una lista de estudios disponibles segun sus caracteristicas de poblacion
     */
    @GET
    @Path("/Dashboard-Encuestado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<ListaEncuestasE> dashboardEncuestado(@PathParam("id") long idusuario) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el dashboard de un encuestado");

        try {
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            DaoUsuario daoUsuario = new DaoUsuario();
            Usuario usuario = daoUsuario.find(idusuario, Usuario.class);
            Dato_usuario encuestado = daoDatoUsuario.find (usuario.get_datoUsuario().get_id(), Dato_usuario.class);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(encuestado.get_fechaNacimiento());
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac = LocalDate.parse(fecha, fmt);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            String aux = String.valueOf(periodo.getYears());

            List<Object[]> estudiosUsuario = daoDatoUsuario.listarDashboardEncuestado(encuestado, aux);

            List<ListaEncuestasE> ResponseListUpdate = new ArrayList<>(estudiosUsuario.size());

            for (Object[] r : estudiosUsuario) {
                ResponseListUpdate.add(new ListaEncuestasE((long)r[0], (String)r[1], (String)r[2], (Date)r[3] ));
            }
            logger.debug("Saliendo del método que consulta el dashboard de un encuestado");
            return ResponseListUpdate;

        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando el dashboard de un encuestado");

        }
    }

    /**
     * Este método retorna los usuarios filtrados por rol o todos los usuarios
     *
     * @param  "id"  id del rol con el cual se desea filtrar
     * @return      una lista de usuarios
     */
    @GET
    @Path("/buscarUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Usuario> obtenerUsuarioRol(@PathParam("id") long idRol ) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los usuarios con un Rol específico");

        try {
            
            List<Usuario> usuarios = null;
            DaoUsuario daoUsuario = new DaoUsuario();

            if(idRol == 0){
                usuarios = daoUsuario.findAll(Usuario.class);
            }else{
                usuarios = daoUsuario.listarUsuarioRol(idRol);
            }
            logger.debug("Saliendo del método que consulta los usuarios con un Rol específico");
            return usuarios;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    /**
     * Este método actualiza la contraseña de un usuario específico
     *
     * @param  id_usuario  id del usuario del cual se actualizará la contraseña
     * @param  clave  contraseña que sustituirá a la contraseña previa
     * @return      un usuarioDto que representa al usuario del cual se cambió su contraseña, con el
     * campo de password actualizado
     */
    @PUT
    @Path("/cambiarPassword/{id_usuario}")
    public UsuarioDto cambiarPassword(@PathParam("id_usuario") long id_usuario, String clave) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza la contraseña de un usuario");
        UsuarioDto resultado = new UsuarioDto();
        try {
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find(id_usuario, Usuario.class);
            usuario.set_password(DigestUtils.md5Hex(clave));
            Usuario resul = dao.update( usuario );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando la contraseña de un usuario");
        }
        logger.debug("Saliendo del método que actualiza la contraseña de un usuario");
        return  resultado;
    }

    @GET
    @Path ("/consultar/{id}")
    public Response consultarUsuario(@PathParam("id") long id) throws  Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un usuario");
        JsonObject resultado;
        try {
            ConsultarUsuarioComando comando=Fabrica.crearComandoConId(ConsultarUsuarioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }




}
