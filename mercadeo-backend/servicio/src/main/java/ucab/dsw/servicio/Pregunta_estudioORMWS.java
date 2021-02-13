package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
import logica.comando.pregunta_estudio.*;
import logica.fabrica.Fabrica;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.entidades.Response.TipoPregunta.*;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.PreguntaEncuestaMapper;
import ucab.dsw.mappers.PreguntaEstudioMapper;

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
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/pregunta_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_estudioORMWS {

    /**
     * Este método registra en el sistema una pregunta asignada a un estudio
     *
     * @param  pregunta_estudioDto  pregunta a ser asignada a un estudio
     * @return      la pregunta_estudioDto que ha sido asignada a un estudio
     */
    @POST
    @Path( "/addPregunta_estudio" )
    public Response addPregunta_estudio(Pregunta_estudioDto pregunta_estudioDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddPregunta_estudioComando comando = Fabrica.crearComandoConEntidad(AddPregunta_estudioComando.class, PreguntaEstudioMapper.mapDtoToEntityInsert(pregunta_estudioDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
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
     * Este método retorna la lista con todas las preguntas asignadas a estudios
     *
     * @return      la lista completa de preguntas asignadas a estudios
     */
    @GET
    @Path("/showPregunta_estudio")
    public Response showPregunta_estudios() throws Exception{
        JsonObject resul;
        try {
            BuscarPregunta_estudioComando comando= Fabrica.crear(BuscarPregunta_estudioComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
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
     * Este método retorna las preguntas asignadas a un estudio
     *
     * @param  "id"  id del estudio del cual se obtendra la lista de preguntas
     * @return      una lista de preguntas asignadas a un estudio
     */
    @GET
    @Path("/mostrarPregunta_estudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerPreguntasDeEstudio(@PathParam("id") long idEstudio) throws Exception {

        try {
            ObtenerPreguntaComando comando= Fabrica.crearComandoConId(ObtenerPreguntaComando.class, idEstudio);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas de un estudio");

        }

    }

    /**
     * Este método retorna la lista de todas las preguntas de la BD que no esten ya asignadas al estudio
     *
     * @param  "id"  id del estudio al cual se le quieren agregar pregunta
     * @return      una lista de preguntas para asignar al estudio
     */
    @GET
    @Path("/preguntasGenerales/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerPreguntasGenerales(@PathParam("id") long idestudio) throws Exception {

        try {
            ObtenerPreguntasGenerales comando= Fabrica.crearComandoConId(ObtenerPreguntasGenerales.class, idestudio);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas generales");

        }

    }

    /**
     * Este método retorna la lista de preguntas recomendada de la BD que no esten ya asignadas al estudio
     *
     * @param  "id"  id del estudio al cual se le quieren agregar pregunta
     * @return      una lista de preguntas para asignar al estudio
     */
    @GET
    @Path("/preguntasRecomendadas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerPreguntasRecomendadas(@PathParam("id") long idestudio) throws Exception {

        try {
            ObtenerPreguntasRecomendadasComando comando= Fabrica.crearComandoConId(ObtenerPreguntasRecomendadasComando.class, idestudio);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas recomendadas para un estudio");

        }

    }

    /**
     * Este método edita en el sistema una pregunta asignada a un estudio
     *
     * @param  pregunta_estudioDto  pregunta a ser editada de un estudio
     * @return      la pregunta_estudioDto que ha sido asignada de un estudio
     */
    @PUT
    @Path( "/updatePregunta_estudio/{id}" )
    public Response updatePregunta_estudio( @PathParam("id") long id , Pregunta_estudioDto pregunta_estudioDto) throws  Exception
    {
        JsonObject resultado;
        try
        {
            EditPregunta_estudioComando comando=Fabrica.crearComandoConEntidad(EditPregunta_estudioComando.class,PreguntaEstudioMapper.mapDtoToEntityUpdate(id,pregunta_estudioDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
     * Este método retorna una pregunta_encuesta para obtener su enunciado, a partir de una pregunta asignada a
     * un estudio
     *
     * @param  id  id de la pregunta relacionada con un estudio de la cual se desea obtener su enunciado
     * @return      la pregunta_encuesta con la cual se relaciona la pregunta asignada al estudio
     */
    @GET
    @Path("/getEnunciadoPregunta/{id}")
    public Response getEnunciadoPregunta(@PathParam("id") long id) throws  Exception{

        try{
            ObtenerEnunciadoPreguntaComando comando= Fabrica.crearComandoConId(ObtenerEnunciadoPreguntaComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando el enunciado de una pregunta");
        }
    }

    /**
     * Este método recibe una lista de preguntas y las asigna a un estudio específico
     *
     * @param  id_estudio  id del estudio al que se desea agregar las preguntas
     * @param  listaPregunta_encuestaDto lista de preguntas que serán asignadas a un estudio
     * @return      el EstudioDto al cual se le asignó la lista de preguntas
     */
    @PUT
    @Path( "/addListaPreguntasEstudio/{id}" )
    public Response addListaPreguntasEstudio(@PathParam("id") long id_estudio, List<Pregunta_encuestaDto> listaPregunta_encuestaDto) throws Exception
    {
        try
        {
            addListaPreguntasComando comando= Fabrica.crearComandoListaConId(addListaPreguntasComando.class, PreguntaEncuestaMapper.mapDtoToEntityInsertList(listaPregunta_encuestaDto), id_estudio);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {

            throw new ucab.dsw.excepciones.CreateException( "Error agregando la lista de preguntas de un estudio");
        }
    }

    /**
     * Este método elimina una pregunta asignada a un estudio
     *
     * @param  "id"  id de la pregunta a ser eliminada
     */
    @PUT
    @Path("/deletePreguntaEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public void deletePreguntaEstudio(@PathParam("id") long idpregunta){

        try {
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            Pregunta_estudio preg = daoPregunta_estudio.find(idpregunta, Pregunta_estudio.class);
            daoPregunta_estudio.delete(preg);

        }catch (Exception e){

            e.printStackTrace();

        }

    }

}
