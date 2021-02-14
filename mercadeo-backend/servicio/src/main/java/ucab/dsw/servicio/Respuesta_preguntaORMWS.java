package ucab.dsw.servicio;

import logica.comando.pregunta_encuesta.InactivarComando;
import logica.comando.producto.ObtenerProductoEstudioComando;
import logica.comando.respuesta_pregunta.*;
import logica.comando.telefono.AddTelefonoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.RespuestaPreguntaMapper;
import ucab.dsw.mappers.TelefonoMapper;
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

@Path( "/respuesta_pregunta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Respuesta_preguntaORMWS {

    private static Logger logger = LoggerFactory.getLogger(Respuesta_preguntaORMWS.class);

    /**
     * Este método registra en el sistema una nueva opción de respuesta para una pregunta
     *
     * @param  respuesta_preguntaDto  opción de respuesta a ser registrada
     * @return      la respuesta_preguntaDto que ha sido registrada y asignada a una pregunta
     */
    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRespuesta_pregunta(Respuesta_preguntaDto respuesta_preguntaDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una respuesta_pregunta");
        JsonObject resultado;
        try
        {
            AddRespuesta_preguntaComando comando = Fabrica.crearComandoConEntidad(AddRespuesta_preguntaComando.class, RespuestaPreguntaMapper.mapDtoToEntityInsert(respuesta_preguntaDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una respuesta_pregunta");
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
     * Este método consulta una opción de respuesta específica
     *
     * @param  id  id de la opción de respuesta a ser consultada
     * @return      la opción de respuesta completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarRespuesta_pregunta(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una respuesta_pregunta");
        JsonObject resultado;
        try {
            ConsultarRespuesta_preguntaComando comando=Fabrica.crearComandoConId(ConsultarRespuesta_preguntaComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una respuesta_pregunta");
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
     * Este método retorna la lista con todas las opciones de respuesta a preguntas
     *
     * @return      la lista completa de opciones de respuesta registradas
     */
    @GET
    @Path("/show")
    public Response showRespuesta_preguntas() {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las respuesta_pregunta");
        JsonObject resul;
        try {
            BuscarRespuesta_preguntaComando comando= Fabrica.crear(BuscarRespuesta_preguntaComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las respuesta_pregunta");
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
     * Este método actualiza una opción de respuesta de una pregunta específica
     *
     * @param  id  id de la opción de respuesta que se va a actualizar
     * @param  respuesta_preguntaDto  respuesta a ser actualizado
     * @return      la respuesta_preguntaDto que ha sido actualizada
     */
    @PUT
    @Path( "/update/{id}" )
    public Response updateRespuesta_pregunta( @PathParam("id") long id , Respuesta_preguntaDto respuesta_preguntaDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una respuesta_pregunta");
        JsonObject resultado;
        try
        {
            EditRespuesta_preguntaComando comando= Fabrica.crearComandoConEntidad(EditRespuesta_preguntaComando.class, RespuestaPreguntaMapper.mapDtoToEntityUpdate(id,respuesta_preguntaDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una respuesta_pregunta");
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
     * Este método retorna la lista de opciones a las que puede responderse de una pregunta específica
     *
     * @param  id  id de la pregunta de la cual se desea obtener sus opciones
     * @return      una lista de respuestas relativas a una pregunta específica
     */
    @GET
    @Path("/showRespuestasPregunta/{id}")
    public Response showRespuesta_preguntas_respuestas(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuestas_pregunta  de una pregunta");
        JsonObject resultado;
        try{
            ObtenerRespuestasPreguntaComando comando= Fabrica.crearComandoConId(ObtenerRespuestasPreguntaComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuestas_pregunta  de una pregunta");
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
     * Este método inactiva una opción de respuesta de una pregunta específica
     *
     * @param  id  id de la opción de respuesta que se desea incativar
     * @param  respuesta_preguntaDto  respuesta que será incativada
     * @return      la respuestaDto que ha sido incativada con el estado actualizado
     */
    @PUT
    @Path( "/inactivar/{id}" )
    public Response incativarRespuesta_pregunta( @PathParam("id") long id , Respuesta_preguntaDto respuesta_preguntaDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que inactiva una respuesta_pregunta");
        JsonObject resultado;
        try
        {
            InactivarRespuestaPreguntaComando comando= Fabrica.crearComandoConId(InactivarRespuestaPreguntaComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que inactiva una respuesta_pregunta");
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
     * Este método recibe una lista de opciones de respuestas para ser agregadas a la BD y relacionadas con una
     * pregunta específica
     *
     * @param  id  id de la pregunta con la que se relacionan las opciones de respuestas
     * @param  listaRespuestas lista de opciones de respuestas que serán agregadas al sistema
     * @return      la pregunta_encuestaDto con la que ese relacionan las respuestas agregadas
     */
    @POST
    @Path( "/addListaRespuestas/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addLista_respuestas(@PathParam("id") long id, List<Respuesta_preguntaDto> listaRespuestas)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una lista de respuesta_preguntas");
        JsonObject resultado;
        try
        {
            addListaRespuestasComando comando = Fabrica.crearComandoLista(addListaRespuestasComando.class, RespuestaPreguntaMapper.mapDtoToEntityInsertList(listaRespuestas, id));
            comando.execute();
            logger.debug("Saliendo del método que agrega una lista de respuesta_preguntas");
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

