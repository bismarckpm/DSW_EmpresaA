package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
import logica.comando.pregunta_encuesta.AddPregunta_encuestaComando;
import logica.comando.pregunta_encuesta.BuscarPregunta_encuestaComando;
import logica.comando.pregunta_encuesta.ConsultarPregunta_encuestaComando;
import logica.comando.pregunta_encuesta.EditPregunta_encuestaComando;
import logica.fabrica.Fabrica;
import ucab.dsw.Response.TipoPregunta.MultipleResponse;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PreguntaEncuestaMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path( "/pregunta_encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_encuestaORMWS {

    private static Logger logger = LoggerFactory.getLogger(Pregunta_encuestaORMWS.class);

    /**
     * Este método registra en el sistema una nueva pregunta al repositorio de preguntas
     *
     * @param  pregunta_encuestaDto  pregunta a ser registrada en el sistema
     * @return      la pregunta_encuestaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPregunta_encuesta(Pregunta_encuestaDto pregunta_encuestaDto) throws  Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una pregunta_encuesta");
        JsonObject resultado;
        try
        {
            AddPregunta_encuestaComando comando = Fabrica.crearComandoConEntidad(AddPregunta_encuestaComando.class, PreguntaEncuestaMapper.mapDtoToEntityInsert(pregunta_encuestaDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una pregunta_encuesta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna la lista con todas las preguntas registradas
     *
     * @return      la lista completa de preguntas registradas
     */
    @GET
    @Path("/show")
    public Response showPregunta_encuestas() throws  Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las pregunta_encuestas");
        JsonObject resul;
        try {
            BuscarPregunta_encuestaComando comando= Fabrica.crear(BuscarPregunta_encuestaComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las pregunta_encuestas");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método actualiza una pregunta específica
     *
     * @param  id  id de la pregunta que se va a actualizar
     * @param  pregunta_encuestaDto  pregunta a ser actualizado
     * @return      la pregunta_enceustaDto que ha sido actualizada
     */
    @PUT
    @Path( "/update/{id}" )
    public Response updatePregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una pregunta_encuesta");
        JsonObject resultado;
        try
        {
            EditPregunta_encuestaComando comando= Fabrica.crearComandoConEntidad(EditPregunta_encuestaComando.class, PreguntaEncuestaMapper.mapDtoToEntityUpdate(id,pregunta_encuestaDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una pregunta_encuesta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método consulta una pregunta específica
     *
     * @param  id  id de la pregunta a ser consultada
     * @return      la pregunta completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarPregunta_encuesta(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una pregunta_encuesta");
        JsonObject resultado;
        try {
            ConsultarPregunta_encuestaComando comando=Fabrica.crearComandoConId(ConsultarPregunta_encuestaComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una pregunta_encuesta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método incativa una pregunta_encuesta
     *
     * @param  id  id de la pregunta que se desea incativar
     * @param  pregunta_encuestaDto pregunta completa que se desea incativar
     * @return      la pregunta_encuestaDto que ha sido inactivada, con su estado actualizado
     */
    @PUT
    @Path( "/inactivar/{id}" )
    public Pregunta_encuestaDto incativarPregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que incativa una pregunta_encuesta");
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_estado( "I" );
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error inactivando una pregunta");
        }
        logger.debug("Saliendo del método que incativa una pregunta_encuesta");
        return  resultado;
    }

    /**
     * Este método retorna la lista de preguntas que tienen opciones personalizadas, es decir,
     * las preguntas de selección simple y de selección múltiple
     *
     * @return      la lista de preguntas que tienen opciones personalizadas
     */
    @GET
    @Path("/showConOpciones")
    public List<Pregunta_encuesta> showPregunta_encuestas_con_opciones() throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las preguntas de selección simple y múltiple");
        List<Pregunta_encuesta> pregunta_encuestas = null;
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            pregunta_encuestas = dao.getConOpciones();
            System.out.println("Pregunta_encuestas:");
            for (Pregunta_encuesta pregunta_encuesta : pregunta_encuestas) {
                System.out.print(pregunta_encuesta.get_id());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_descripcion());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_tipoPregunta());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_usuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas que poseen opciones personalizadas");
        }
        logger.debug("Saliendo del método que consulta las preguntas de selección simple y múltiple");
        return pregunta_encuestas;
    }
}
