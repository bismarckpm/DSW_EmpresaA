package ucab.dsw.servicio;

import logica.comando.solicitud_estudio.*;
import logica.fabrica.Fabrica;
import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.entidades.Response.TipoPregunta.ProductoSolicitudResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.SolicitudEstudioMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path( "/solicitud_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Solicitud_estudioORMWS {

    private static Logger logger = LoggerFactory.getLogger(Solicitud_estudioORMWS.class);

    /**
     * Este método registra en el sistema una nueva solicitud de estudio
     *
     * @param  solicitud_estudioDto  solicitud de estudio a ser registrada
     * @return      la solicitud_estudioDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    public Response addSolicitud_estudio(Solicitud_estudioDto solicitud_estudioDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una solicitud de estudio");
        JsonObject resultado;
        try
        {
            AddSolicitud_estudioComando comando = Fabrica.crearComandoConEntidad(AddSolicitud_estudioComando.class, SolicitudEstudioMapper.mapDtoToEntityInsert(solicitud_estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una solicitud de estudio");
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
     * Este método consulta una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio a ser consultada
     * @return      la solicitud de estudio completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarSolicitud_estudio(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una solicitud de estudio");
        JsonObject resultado;
        try {
            ConsultarSolicitud_estudioComando comando=Fabrica.crearComandoConId(ConsultarSolicitud_estudioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una solicitud de estudio");
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
     * Este método retorna la lista con todas las solicitudes de estudio
     *
     * @return      la lista completa de solicitudes de estudio registradas
     */
    @GET
    @Path("/buscar")
    public Response showSolicitud_estudios(){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las solicitudes de estudio");
        JsonObject resul;
        try {
            BuscarSolicitud_estudioComando comando= Fabrica.crear(BuscarSolicitud_estudioComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las solicitudes de estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método retorna el producto asociado a una solicitud
     *
     * @param  idSolicitud  id de la solicitud
     * @return      el producto asociado a una solicitud
     */
    @GET
    @Path("/ProductoDeSolicitud/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerProductoSolicitud(@PathParam("id") long idSolicitud) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el producto de una solicitud de estudio");
        JsonObject resultado;
        try {
            ObtenerProductoSolicitudComando comando=Fabrica.crearComandoConId(ObtenerProductoSolicitudComando.class,idSolicitud);
            comando.execute();
            logger.debug("Saliendo del método que consulta el producto de una solicitud de estudio");
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
     * Este método actualiza una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio que se va a actualizar
     * @param  solicitud_estudioDto  solicitud de estudio a ser actualizada
     * @return      la solicitud_estudioDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response updateSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una solicitud de estudio");
        JsonObject resultado;
        try
        {
            EditSolicitud_estudioComando comando=Fabrica.crearComandoConEntidad(EditSolicitud_estudioComando.class,SolicitudEstudioMapper.mapDtoToEntityUpdate(id,solicitud_estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una solicitud de estudio");
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
     * Este método retorna las solicitudes de estudio de un cliente
     *
     * @param  id  id del cliente del cual se desea obtener sus solicitudes de estudio
     * @return      una lista de solicitudes de estudio pertenecientes a un cliente
     */
    @GET
    @Path("/showSolicitudUsuario/{id}")
    public Response showSolicitud_estudio_usuario(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una solicitud de estudio");
        JsonObject resultado;
        try{
            ObtenerSolicitudUsuarioComando comando=Fabrica.crearComandoConId(ObtenerSolicitudUsuarioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una solicitud de estudio");
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
     * Este método inactiva en el sistema una solicitud de estudio
     *
     * @param  id  id de solicitud de estudio a ser inativada
     * @param  solicitud_estudioDto  solicitud de estudio a ser inactivada
     * @return      la solicitud_estudioDto que ha sido inactivada en el sistema
     */
    @PUT
    @Path( "/inactivar/{id}" )
    public Response inactivarSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que incativa una solicitud de estudio");
        JsonObject resultado;
        try
        {
            InactivarSolicitudComando comando=Fabrica.crearComandoConId(InactivarSolicitudComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que incativa una solicitud de estudio");
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
     * Este método retorna los estudios relacionados con una solicitud
     *
     * @param  id_solicitud  id de la solicitud de estudio de la cual se desea obtener sus estudios
     * @return      una lista de estudios
     */
    @GET
    @Path("/getEstudiosDeSolicitud/{id_solicitud}")
    public Response getEstudiosDeSolicitud(@PathParam("id_solicitud") long id_solicitud) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el estudio de una solicitud");
        JsonObject resultado;
        try{
            ObtenerEstudiosSolicitudComando comando=Fabrica.crearComandoConId(ObtenerEstudiosSolicitudComando.class,id_solicitud);
            comando.execute();
            logger.debug("Saliendo del método que consulta el estudio de una solicitud");
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
