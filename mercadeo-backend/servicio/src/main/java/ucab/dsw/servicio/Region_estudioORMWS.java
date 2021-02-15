package ucab.dsw.servicio;

import logica.comando.producto.ObtenerProductoEstudioComando;
import logica.comando.region_estudio.*;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.RegionEstudioMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/region_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Region_estudioORMWS {

    private static Logger logger = LoggerFactory.getLogger(Region_estudioORMWS.class);

    /**
     * Este método consulta una región de estudio específica
     *
     * @param  id  id de la región de estudio a ser consultada
     * @return      la región de estudio completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarRegion_estudio(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una región de estudio");
        JsonObject resultado;
        try {
            ConsultarRegion_estudioComando comando=Fabrica.crearComandoConId(ConsultarRegion_estudioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que agrega una región de estudio");
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
     * Este método retorna la lista con todas las regiones de estudio de todas las solicitudes de estudio
     *
     * @return      la lista completa de regiones de estudio registradas
     */
    @GET
    @Path("/buscar")
    public Response showRegion_estudio()
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las regiones de estudio");
        JsonObject resul;
        try {
            BuscarRegion_estudioComando comando= Fabrica.crear(BuscarRegion_estudioComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las regiones de estudio");
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
     * Este método recibe una lista de lugares para registrar las regiones de estudio de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio a la que serán agregadas las regiones de estudio
     * @param  listaLugares lista de lugares con los que irá relacionada cada región de estudio
     * @return      la solicitud_estudioDto con la que ese relacionan las regiones de estudio agregadas
     */
    @POST
    @Path( "/addRegionesASolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaLugares)throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una lista de regiones de estudio");
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        JsonObject resul;
        try
        {
            AddRegion_estudioComando comando=Fabrica.crearComandoLista(AddRegion_estudioComando.class,RegionEstudioMapper.mapDtoToEntityInsertList(listaLugares,id));
            comando.execute();
            logger.debug("Saliendo del método que agrega una lista de regiones de estudio");
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
     * Este método retorna las regiones de estudio de una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio de la cual se desea obtener sus regiones de estudio
     * @return      la lista de lugares con los que se relacionan las regiones de estudio de una solicitud de estudio
     */
    @GET
    @Path("/getRegionesDeSolicitud/{id}")
    public Response getRegionesDeSolicitud(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las regiones de estudiod e una solicitud de estudio");
        JsonObject resultado;
        try{
            ObtenerRegionesEstudioComando comando= Fabrica.crearComandoConId(ObtenerRegionesEstudioComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las regiones de estudiod e una solicitud de estudio");
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
     * Este método actualiza las regiones de estudio de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio a la cual se le actualizarán las regiones de estudio
     * @param  listaLugares lista de lugares con los cuales se actualizarán las regiones de estudio de la solicitud
     * @return      la solicitud_estudioDto a la cual se le actualizaron sus regiones de estudio
     */
    @POST
    @Path( "/updateRegionesDeSolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaLugares)
    {
        JsonObject resultado;
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza las regiones de estudio de una solicitud");
        try
        {
            EditRegion_estudioComando comando=Fabrica.crearComandoListaConId(EditRegion_estudioComando.class,RegionEstudioMapper.mapDtoToEntityInsertList(listaLugares,id),id);
            comando.execute();
            logger.debug("Saliendo del método que actualiza las regiones de estudio de una solicitud");
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
