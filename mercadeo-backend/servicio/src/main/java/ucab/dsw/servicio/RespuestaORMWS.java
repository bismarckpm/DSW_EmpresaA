package ucab.dsw.servicio;

import logica.comando.producto.AddProductoComando;
import logica.comando.respuesta.*;
import logica.fabrica.Fabrica;
import lombok.extern.java.Log;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.EstudioUsuarioResponse;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.mappers.ProductoMapper;
import ucab.dsw.mappers.RespuestaMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Log
@Path( "/respuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaORMWS {

    private Logger logger = Logger.getLogger(UsuarioORMWS.class.getName());


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

        try {
            ObtenerPreguntasEncuestaComando comando = Fabrica.crearComandoCon2Id(ObtenerPreguntasEncuestaComando.class, id, idUsuario);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de preguntas de una encuesta");

        }

    }

    @GET
    @Path("/validarEstatus/{id}/{idUsuario}")
    public Response validarEstatusEncuesta(@PathParam("id") long idEstudio, @PathParam("idUsuario") long idUsuario) throws Exception {
        try {
            ValidarEstatusEncuestaComando comando = Fabrica.crearComandoCon2Id(ValidarEstatusEncuestaComando.class, idEstudio, idUsuario);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las respuestas de las preguntas de una encuesta");

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

        try {
            RespuestasEncuestaComando comando = Fabrica.crearComandoConId(RespuestasEncuestaComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las respuestas de las preguntas de una encuesta");

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
        try{
            AddRespuestaComando comando = Fabrica.crearComandoConEntidad(AddRespuestaComando.class, RespuestaMapper.mapDtoToEntityInsert(respuestaDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando una respuesta a una pregunta de un estudio");
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
        try{
            ObtenerRespuestasSimpleComando comando = Fabrica.crearComandoConId(ObtenerRespuestasSimpleComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas de selección simple de un estudio");
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
        try{
            ContarRespuestasSimplesComando comando = Fabrica.crearComandoConId(ContarRespuestasSimplesComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de usuarios que respondieron una pregunta de selección simple");
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
        try{
            ObtenerRespuestasMultiple comando = Fabrica.crearComandoConId(ObtenerRespuestasMultiple.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas de selección múltiple de un estudio");
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
        try{
            ContarRespuestasMultiplesComando comando = Fabrica.crearComandoConId(ContarRespuestasMultiplesComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de usuarios que respondieron una pregunta de selección múltiple");
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
        try{
            ObtenerRespuestasVoFComando comando = Fabrica.crearComandoConId(ObtenerRespuestasVoFComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas de verdadero o falso de un estudio");
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
        try{
            ContarREspuestasVoFComando comando = Fabrica.crearComandoConId(ContarREspuestasVoFComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de usuarios que respondieron una pregunta de Verdadero o falso");
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
        try{
            ObtenerRespuestasAbiertas comando = Fabrica.crearComandoConId(ObtenerRespuestasAbiertas.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas abiertas de un estudio");
        }
    }


}
