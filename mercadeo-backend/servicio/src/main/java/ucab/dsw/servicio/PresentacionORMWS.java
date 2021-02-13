package ucab.dsw.servicio;

import logica.comando.presentacion.AddPresentacionComando;
import logica.comando.presentacion.BuscarPresentacionComando;
import logica.comando.presentacion.ConsultarPresentacionComando;
import logica.comando.presentacion.EditPresentacionComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PresentacionMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentacionORMWS {

    private static Logger logger = LoggerFactory.getLogger(PresentacionORMWS.class);

    /**
     * Este método registra en el sistema una nueva presentación de producto
     *
     * @param  presentacionDto  presentación de producto a ser registrada en el sistema
     * @return      la presentacionDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/addPresentacion" )
    public Response addPresentacion(PresentacionDto presentacionDto ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una presentación");
        JsonObject resultado;
        try
        {
            AddPresentacionComando comando = Fabrica.crearComandoConEntidad(AddPresentacionComando.class, PresentacionMapper.mapDtoToEntityInsert(presentacionDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una presentación");
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
     * Este método retorna la lista con todas las presentaciones de productos
     *
     * @return      la lista completa de presentaciones de producto registradas
     */
    @GET
    @Path("/showPresentacion")
    public Response showPresentaciones() throws  Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las presentaciones");
        JsonObject resul;
        try {
            BuscarPresentacionComando comando= Fabrica.crear(BuscarPresentacionComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las presentaciones");
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
     * Este método consulta una presentación de producto específica
     *
     * @param  id  id de la presentación de producto a ser consultada
     * @return      la presentación de producto completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarPresentacion(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una presentación");
        JsonObject resultado;
        try {
            ConsultarPresentacionComando comando=Fabrica.crearComandoConId(ConsultarPresentacionComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una presentación");
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
     * Este método actualiza una presentación de producto específica
     *
     * @param  id  id de la presentación que se va a actualizar
     * @param  presentacionDto  presentación a ser actualizado
     * @return      la presentacionDto que ha sido actualizada
     */
    @PUT
    @Path( "/updatePresentacion/{id}" )
    public Response updatePresentacion( @PathParam("id") long id , PresentacionDto presentacionDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una presentación");
        JsonObject resultado;
        try
        {
            EditPresentacionComando comando= Fabrica.crearComandoConEntidad(EditPresentacionComando.class, PresentacionMapper.mapDtoToEntityUpdate(id,presentacionDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una presentación");
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
