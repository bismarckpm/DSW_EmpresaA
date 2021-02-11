package ucab.dsw.servicio;

import logica.comando.nivel_economico.AddNivel_economicoComando;
import logica.comando.nivel_economico.BuscarNivel_economicoComando;
import logica.comando.nivel_economico.EditNivel_economicoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.NivelEconomicoMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/nivelEconomico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Nivel_economicoORMWS {

    private static Logger logger = LoggerFactory.getLogger(Nivel_economicoORMWS.class);

    /**
     * Este método registra en el sistema un nuevo nivel económico
     *
     * @param  nivel_economicoDto  nivel económico a ser registrado
     * @return      el nivel_economicoDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Response addNivel_economico(Nivel_economicoDto nivel_economicoDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un nivel económico");
        JsonObject resultado;
        try
        {
            AddNivel_economicoComando comando = Fabrica.crearComandoConEntidad(AddNivel_economicoComando.class, NivelEconomicoMapper.mapDtoToEntityInsert(nivel_economicoDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un nivel económico");
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

    /**
     * Este método retorna la lista con todas los niveles económicos
     *
     * @return      la lista completa de niveles económicos registrados
     */
    @GET
    @Path("/buscar")
    public Response showNivel_economico () 
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los niveles económicos");
        JsonObject resul;
        try {
            BuscarNivel_economicoComando comando= Fabrica.crear(BuscarNivel_economicoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los niveles económicos");
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
     * Este método actualiza un nivel económico específico
     *
     * @param  nivel_economicoDto  nivel económico a ser actualizado
     * @return      el nivel_economicoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editNivel_economico( Nivel_economicoDto nivel_economicoDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un nivel económico");
        JsonObject resultado;
        try
        {
            EditNivel_economicoComando comando= Fabrica.crearComandoConEntidad(EditNivel_economicoComando.class, NivelEconomicoMapper.mapDtoToEntityUpdate(nivel_economicoDto.getId(),nivel_economicoDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un nivel económico");
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
