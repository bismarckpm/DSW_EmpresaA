package ucab.dsw.servicio;

import logica.comando.producto.AddProductoComando;
import logica.comando.respuesta.*;
import logica.fabrica.Fabrica;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.EstudioUsuarioResponse;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.ProductoMapper;
import ucab.dsw.mappers.RespuestaMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path( "/respuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaORMWS {

    private static Logger logger = LoggerFactory.getLogger(RespuestaORMWS.class);

    /**
     * Este método lista todas las preguntas de una encuesta
     *
     * @param  "id"  id del estudio
     * @return      la lista de preguntas que posee asignado la pregunta_estudio de ese estudio
     */
    @GET
    @Path("/preguntas/{id}/{idUsuario}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerPreguntaEncuesta(@PathParam("id") long id, @PathParam("idUsuario") long idUsuario) throws Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las preguntas de una encuesta");
        JsonObject resultado;
        try {
            ObtenerPreguntasEncuestaComando comando = Fabrica.crearComandoCon2Id(ObtenerPreguntasEncuestaComando.class, id, idUsuario);
            comando.execute();
            logger.debug("Saliendo del método que consulta las preguntas de una encuesta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch(CustomException ex){
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
    @Path("/validarEstatus/{id}/{idUsuario}")
    public Response validarEstatusEncuesta(@PathParam("id") long idEstudio, @PathParam("idUsuario") long idUsuario) throws Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el estatus de una encuesta");
        JsonObject resultado;
        try {
            ValidarEstatusEncuestaComando comando = Fabrica.crearComandoCon2Id(ValidarEstatusEncuestaComando.class, idEstudio, idUsuario);
            comando.execute();
            logger.debug("Saliendo del método que consulta el estatus de una encuesta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch(CustomException ex){
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
     * Este método lista todas las respuestas de una encuesta
     *
     * @param  "id"  id del estudio
     * @return      la lista de respuestas que posee asignado la pregunta_estudio de ese estudio
     */
    @GET
    @Path("/respuestas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerRespuestaEncuesta(@PathParam("id") long id) throws Exception {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuesta de una encuesta");
        JsonObject resultado;
        try {
            RespuestasEncuestaComando comando = Fabrica.crearComandoConId(RespuestasEncuestaComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuesta de una encuesta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch(CustomException ex){
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
     * Este método registra en el sistema todas las respuestas de una encuesta
     *
     * @param  "List<RespuestaDto>"  lista de Respuestas a ser registrada
     * @return      la RespuestaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRespuesta(RespuestaDto respuestaDto ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una respuesta");
        JsonObject resultado;
        try{
            AddRespuestaComando comando = Fabrica.crearComandoConEntidad(AddRespuestaComando.class, RespuestaMapper.mapDtoToEntityInsert(respuestaDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una respuesta");
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
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta de
     * selección simple
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta de
     * selección simple de un estudio específico
     */
    @Path("/showRespuestasAPreguntaSimple/{id}")
    public Response showRespuestasAPreguntaSimple(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuestas a preguntas de selección simple");
        JsonObject resultado;
        try{
            ObtenerRespuestasSimpleComando comando = Fabrica.crearComandoConId(ObtenerRespuestasSimpleComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuestas a preguntas de selección simple");
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
     * Este método retorna la cantidad de personas que respondieron a una opción de una pregunta de
     * selección simple de un estudio específico
     *
     * @param  id  id de la respuesta de la cual se desea contar los encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción de una
     * pregunta de selección simple de un estudio
     */
    @GET
    @Path("/contarRespuestasSimples/{id}")
    public Response contarRespuestasSimples(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la cantidad de respuestas a preguntas de selección simple");
        JsonObject resultado;
        try{
            ContarRespuestasSimplesComando comando = Fabrica.crearComandoConId(ContarRespuestasSimplesComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta la cantidad de respuestas a preguntas de selección simple");
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
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta de
     * selección múltiple
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta de
     * selección múltiple de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaMultiple/{id}")
    public Response showRespuestasAPreguntaMultiple(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuestas a una pregunta de selección múltiple");
        JsonObject resultado;
        try{
            ObtenerRespuestasMultiple comando = Fabrica.crearComandoConId(ObtenerRespuestasMultiple.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuestas a una pregunta de selección múltiple");
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
     * Este método retorna la cantidad de personas que respondieron a una opción de una pregunta de
     * selección múltiple de un estudio específico
     *
     * @param  id  id de la respuesta de la cual se desea contar los encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción de una
     * pregunta de selección múltiple de un estudio
     */
    @GET
    @Path("/contarRespuestasMultiples/{id}")
    public Response contarRespuestasMultiples(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que cuenta la cantidad de respuestas a una pregunta de selección múltiple");
        JsonObject resultado;
        try{
            ContarRespuestasMultiplesComando comando = Fabrica.crearComandoConId(ContarRespuestasMultiplesComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que cuenta la cantidad de respuestas a una pregunta de selección múltiple");
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
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta de
     * verdadero o falso
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta de
     * verdadero o falso de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaVF/{id}")
    public Response showRespuestasAPreguntaVF(@PathParam("id") long id) throws  Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuesta a una pregunta de verdadero o falso");
        JsonObject resultado;
        try{
            ObtenerRespuestasVoFComando comando = Fabrica.crearComandoConId(ObtenerRespuestasVoFComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuesta a una pregunta de verdadero o falso");
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
     * Este método retorna la cantidad de personas que respondieron a una opción de una pregunta de
     * verdadero o falso de un estudio específico
     *
     * @param  id  id de la respuesta de la cual se desea contar los encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción de una
     * pregunta de verdadero o falso de un estudio
     */
    @GET
    @Path("/contarRespuestasVF/{id}")
    public Response contarRespuestasVF(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la cantidad de respuestas a una pregunta de verdadero o falso");
        JsonObject resultado;
        try{
            ContarREspuestasVoFComando comando = Fabrica.crearComandoConId(ContarREspuestasVoFComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta la cantidad de respuestas a una pregunta de verdadero o falso");
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
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta abierta
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta abierta de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaAbierta/{id}")
    public Response showRespuestasAPreguntaAbierta(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuestas a una pregunta abierta");
        JsonObject resultado;
        try{
            ObtenerRespuestasAbiertas comando = Fabrica.crearComandoConId(ObtenerRespuestasAbiertas.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuestas a una pregunta abierta");
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
