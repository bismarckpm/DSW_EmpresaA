package ucab.dsw.servicio;

import Implementation.ImpLdap;
import logica.comando.usuario.*;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.ExistUserException;
import ucab.dsw.mappers.UsuarioMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioORMWS {

    private static Logger logger = LoggerFactory.getLogger(UsuarioORMWS.class);

    private ImpLdap impLdap = new ImpLdap();


    /**
     * Este método registra en el sistema un nuevo usuario
     *
     * @param  "UsuarioDto"  usuario a ser registrada
     * @return      la UsuarioDto que ha sido registrada en el sistema
     */
    @POST
    @Path("/crear")
    public Response create(UsuarioDto usuarioDto){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un usuario");
        JsonObject resultado;
        try {
            AddUsuarioComando comando = Fabrica.crearComandoConEntidad(AddUsuarioComando.class, UsuarioMapper.mapDtoToEntityInsert(usuarioDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        } catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
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
    public Response updateUsuario(@PathParam("id") long id , UsuarioDto usuarioDto)
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
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

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
    public Response authenticate(LoginDto loginDto){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que autentica un usuario");
        JsonObject resultado;
        try {
            AutenticarComando comando = Fabrica.crearComandoAutenticar(AutenticarComando.class, loginDto);
            comando.execute();
            logger.debug("Saliendo del método que autentica un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        } catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
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
    public Response getAll(@PathParam("id") long idRol) throws Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un usuario");
        JsonObject resultado;
        try {
            ObtenerUsuarioRolComando comando=Fabrica.crearComandoConId(ObtenerUsuarioRolComando.class,idRol);
            comando.execute();
            logger.debug("Saliendo del método que consulta un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        } catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
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
    public Response dashboardEncuestado(@PathParam("id") long idusuario){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el dashboard de un encuestado");
        JsonObject resultado;
        try {
            ObtenerEstudiosEncuestadoComando comando = Fabrica.crearComandoConId(ObtenerEstudiosEncuestadoComando.class,idusuario);
            comando.execute();
            logger.debug("Saliendo del método que consulta el dashboard de un encuestado");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        } catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
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
    public Response obtenerUsuarioRol(@PathParam("id") long idRol ) throws Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los usuarios con un rol específico");
        JsonObject resultado;
        try {
            BuscarUsuarioRolComando comando=Fabrica.crearComandoConId(BuscarUsuarioRolComando.class,idRol);
            comando.execute();
            logger.debug("Saliendo del método que consulta los usuarios con un rol específico");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        } catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
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
    public Response cambiarPassword(@PathParam("id_usuario") long id_usuario, String clave) throws Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza la contraseña de un usuario");
        JsonObject resultado;
        try {
            CambiarContraseñaComando comando=Fabrica.crearComandoString(CambiarContraseñaComando.class,id_usuario,clave);
            comando.execute();
            logger.debug("Saliendo del método que actualiza la contraseña de un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
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
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }
}
