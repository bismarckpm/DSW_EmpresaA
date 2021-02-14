package ucab.dsw.servicio;
import logica.comando.tipo.AddTipoComando;
import logica.comando.tipo.BuscarTipoComando;
import logica.comando.tipo.ConsultarTipoComando;
import logica.comando.tipo.EditTipoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.TipoMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/tipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TipoORMWS {

    private static Logger logger = LoggerFactory.getLogger(TipoORMWS.class);

    private DaoTipo daoTipo = new DaoTipo();

    /**
     * Este método registra en el sistema un nuevo tipo de producto
     *
     * @param  tipoDto  tipo de producto a ser registrado
     * @return      el tipoDto que ha sido registrado en el sistema
     */
    @POST
    @Path( "/agregar" )
    public Response addTipo(TipoDto tipoDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un tipo de producto");
        JsonObject resultado;
        try
        {
            AddTipoComando comando = Fabrica.crearComandoConEntidad(AddTipoComando.class, TipoMapper.mapDtoToEntityInsert(tipoDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un tipo de producto");
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
     * Este método retorna la lista con todos los tipos de producto
     *
     * @return      la lista completa de tipos de productos registrados
     */
    @GET
    @Path("/buscar")
    public Response showTipo()
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los tipos de productos");
        JsonObject resul;
        try {
            BuscarTipoComando comando= Fabrica.crear(BuscarTipoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta los tipos de productos");
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
     * Este método consulta un tipo de producto específico
     *
     * @param  id  id del producto a ser consultado
     * @return      el producto completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarTipo(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un tipo de producto");
        JsonObject resultado;
        try {
            ConsultarTipoComando comando=Fabrica.crearComandoConId(ConsultarTipoComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un tipo de producto");
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
     * Este método actualiza un tipo de producto específico
     *
     * @param  tipoDto  tipo de proudcto a ser actualizado
     * @return      el tipoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editTipo( TipoDto tipoDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un tipo de producto");
        JsonObject resultado;
        try
        {
            EditTipoComando comando= Fabrica.crearComandoConEntidad(EditTipoComando.class, TipoMapper.mapDtoToEntityUpdate(tipoDto.getId(),tipoDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un tipo de producto");
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
