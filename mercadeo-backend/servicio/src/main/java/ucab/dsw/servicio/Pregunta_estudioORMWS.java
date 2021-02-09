package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
import logica.comando.pregunta_estudio.AddPregunta_estudioComando;
import logica.comando.pregunta_estudio.BuscarPregunta_estudioComando;
import logica.comando.pregunta_estudio.EditPregunta_estudioComando;
import logica.fabrica.Fabrica;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.Response.TipoPregunta.*;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.entidades.*;
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
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/pregunta_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_estudioORMWS {

    private static Logger logger = LoggerFactory.getLogger(Pregunta_estudioORMWS.class);

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

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una Pregunta_estudio");
        JsonObject resultado;
        try
        {
            AddPregunta_estudioComando comando = Fabrica.crearComandoConEntidad(AddPregunta_estudioComando.class, PreguntaEstudioMapper.mapDtoToEntityInsert(pregunta_estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una Pregunta_estudio");
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

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las Preguntas_estudio");
        JsonObject resul;
        try {
            BuscarPregunta_estudioComando comando= Fabrica.crear(BuscarPregunta_estudioComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las Preguntas_estudio");
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
    public List<PreguntasResponse> obtenerPreguntasDeEstudio(@PathParam("id") long idEstudio) throws Exception {


        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una Pregunta_estudio");
        try {
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<Object[]> preguntas = daoPregunta_estudio.listarPreguntasDeEstudio(idEstudio);

            List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntas.size());

            for (Object[] r : preguntas) {
                ResponseListUpdate.add(new PreguntasResponse((long)r[0], (String)r[1], (String)r[2]));
            }
            logger.debug("Saliendo del método que consulta una Pregunta_estudio");
            return ResponseListUpdate;
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
    public List<PreguntasResponse> obtenerPreguntasGenerales(@PathParam("id") long idestudio) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las preguntas generales");

        try {
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<Object[]> preguntasGenerales = daoPregunta_estudio.listarPreguntasGenerales(idestudio);

            List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntasGenerales.size());

            for (Object[] r : preguntasGenerales) {
                ResponseListUpdate.add(new PreguntasResponse((long)r[0], (String)r[1], (String)r[2]));
            }
            logger.debug("Saliendo del método que consulta las preguntas generales");
            return ResponseListUpdate;
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
    public List<PreguntasResponse> obtenerPreguntasRecomendadas(@PathParam("id") long idestudio) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las preguntas recomendadas para un estudio");

        try {
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<Object[]> preguntasRecomendadas = daoPregunta_estudio.listarPreguntasRecomendadas(idestudio);

            List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntasRecomendadas.size());

            for (Object[] r : preguntasRecomendadas) {
                ResponseListUpdate.add(new PreguntasResponse((long)r[0], (String)r[1], (String)r[2]));
            }
            logger.debug("Saliendo del método que consulta las preguntas recomendadas para un estudio");
            return ResponseListUpdate;
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

        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una Pregunta_estudio");
        JsonObject resultado;
        try
        {
            EditPregunta_estudioComando comando=Fabrica.crearComandoConEntidad(EditPregunta_estudioComando.class,PreguntaEstudioMapper.mapDtoToEntityUpdate(id,pregunta_estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una Pregunta_estudio");
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
    public List<Pregunta_encuesta> getEnunciadoPregunta(@PathParam("id") long id) throws  Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el enunciado de una Pregunta_estudio");
        List<Pregunta_encuesta> pregunta_encuesta = null;
        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
            pregunta_encuesta = daoPregunta_encuesta.getEnunciadoPregunta(dao.find(id, Pregunta_estudio.class));
            System.out.println("Pregunta_encuestas:");
            for (Pregunta_encuesta pregunta_encuestax : pregunta_encuesta) {
                System.out.print(pregunta_encuestax.get_id());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_descripcion());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_tipoPregunta());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_usuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando el enunciado de una pregunta");
        }
        logger.debug("Saliendo del método que consulta el enunciado de una Pregunta_estudio");
        return pregunta_encuesta;
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
    public EstudioDto addListaPreguntasEstudio(@PathParam("id") long id_estudio, List<Pregunta_encuestaDto> listaPregunta_encuestaDto) throws Exception
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una lista de Pregunta_estudio a un estudio");
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            DaoEstudio daoEstudio = new DaoEstudio();
            Estudio estudio = daoEstudio.find(id_estudio, Estudio.class);
            resultado.setId(estudio.get_id());
            for (Pregunta_encuestaDto pregunta_encuestaAux : listaPregunta_encuestaDto) {
                Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
                pregunta_estudio.set_pregunta( pregunta_encuestaAux.getDescripcion() );
                pregunta_estudio.set_estado( "A" );
                DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
                Pregunta_encuesta pregAux = daoPregunta_encuesta.find(pregunta_encuestaAux.getId(), Pregunta_encuesta.class);
                pregunta_estudio.set_preguntaEncuesta( pregAux);
                pregunta_estudio.set_estudio(estudio);
                Pregunta_estudio resul = dao.insert( pregunta_estudio );
            }
        }
        catch ( Exception ex )
        {

            throw new ucab.dsw.excepciones.CreateException( "Error agregando la lista de preguntas de un estudio");
        }
        logger.debug("Saliendo del método que agrega una lista de Pregunta_estudio a un estudio");
        return  resultado;
    }

}
