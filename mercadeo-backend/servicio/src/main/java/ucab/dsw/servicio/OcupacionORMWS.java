package ucab.dsw.servicio;

import logica.comando.ocupacion.AddOcupacionComando;
import logica.comando.ocupacion.BuscarOcupacionComando;
import logica.comando.ocupacion.EditOcupacionComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.OcupacionMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionORMWS {

    private static Logger logger = LoggerFactory.getLogger(OcupacionORMWS.class);

    /**
     * Este método registra en el sistema una nueva ocupación
     *
     * @param  ocupacionDto ocupación a ser registrada
     * @return      la ocupacionDto que ha sido registrada en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Response addOcupacion(OcupacionDto ocupacionDto ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una ocupación");
        JsonObject resultado;
        try
        {
            AddOcupacionComando comando = Fabrica.crearComandoConEntidad(AddOcupacionComando.class, OcupacionMapper.mapDtoToEntityInsert(ocupacionDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            System.out.println("Código de error: " + ex.getCodigo());
            System.out.println("Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
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
     * Este método retorna la lista con todas las ocupaciones
     *
     * @return      la lista completa de ocupaciones registradas
     */
    @GET
    @Path("/buscar")
    public Response showOcupacion() throws  Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las ocupaciones");
        JsonObject resul;
        try {
            BuscarOcupacionComando comando= Fabrica.crear(BuscarOcupacionComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resul= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método actualiza una ocupación específica
     *
     * @param  ocupacionDto  ocupación a ser actualizada
     * @return      la ocupacionDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editOcupacion( OcupacionDto ocupacionDto) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una ocupación");
        JsonObject resultado;
        try
        {
            EditOcupacionComando comando=Fabrica.crearComandoConEntidad(EditOcupacionComando.class,OcupacionMapper.mapDtoToEntityUpdate(ocupacionDto.getId(),ocupacionDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch(CustomException ex){
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
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

}
