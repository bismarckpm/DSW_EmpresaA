package ucab.dsw.servicio;

import logica.comando.respuesta_pregunta.AddRespuesta_preguntaComando;
import logica.comando.respuesta_pregunta.BuscarRespuesta_preguntaComando;
import logica.comando.respuesta_pregunta.ConsultarRespuesta_preguntaComando;
import logica.comando.respuesta_pregunta.EditRespuesta_preguntaComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.RespuestaPreguntaMapper;

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
    public Response addRespuesta_pregunta(Respuesta_preguntaDto respuesta_preguntaDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddRespuesta_preguntaComando comando = Fabrica.crearComandoConEntity(AddRespuesta_preguntaComando.class, RespuestaPreguntaMapper.mapDtoToEntityInsert(respuesta_preguntaDto));
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
     * Este método consulta una opción de respuesta específica
     *
     * @param  id  id de la opción de respuesta a ser consultada
     * @return      la opción de respuesta completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarRespuesta_pregunta(@PathParam("id") long id) throws Exception{
        JsonObject resultado;
        try {
            ConsultarRespuesta_preguntaComando comando=Fabrica.crearComandoConId(ConsultarRespuesta_preguntaComando.class,id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

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
    public Response showRespuesta_preguntas() throws Exception{
        JsonObject resul;
        try {
            BuscarRespuesta_preguntaComando comando= Fabrica.crear(BuscarRespuesta_preguntaComando.class);
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
     * Este método actualiza una opción de respuesta de una pregunta específica
     *
     * @param  id  id de la opción de respuesta que se va a actualizar
     * @param  respuesta_preguntaDto  respuesta a ser actualizado
     * @return      la respuesta_preguntaDto que ha sido actualizada
     */
    @PUT
    @Path( "/update/{id}" )
    public Response updateRespuesta_pregunta( @PathParam("id") long id , Respuesta_preguntaDto respuesta_preguntaDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            EditRespuesta_preguntaComando comando= Fabrica.crearComandoBoth(EditRespuesta_preguntaComando.class,respuesta_preguntaDto.getId(), RespuestaPreguntaMapper.mapDtoToEntityUpdate(respuesta_preguntaDto.getId(),respuesta_preguntaDto));
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
     * Este método retorna la lista de opciones a las que puede responderse de una pregunta específica
     *
     * @param  id  id de la pregunta de la cual se desea obtener sus opciones
     * @return      una lista de respuestas relativas a una pregunta específica
     */
    @GET
    @Path("/showRespuestasPregunta/{id}")
    public List<Respuesta_pregunta> showRespuesta_preguntas_respuestas(@PathParam("id") long id) throws Exception{
        List<Respuesta_pregunta> respuesta_preguntas = null;
        try{
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            DaoPregunta_encuesta daoPregunta = new DaoPregunta_encuesta();
            respuesta_preguntas = dao.getRespuestasPregunta(daoPregunta.find(id, Pregunta_encuesta.class));
            System.out.println("Respuesta_preguntas:");
            for (Respuesta_pregunta respuesta_pregunta : respuesta_preguntas) {
                System.out.print(respuesta_pregunta.get_id());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_nombre());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_preguntaEncuesta().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando las opciones de respuesta de una pregunta");
        }
        return respuesta_preguntas;
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
    public Respuesta_preguntaDto incativarRespuesta_pregunta( @PathParam("id") long id , Respuesta_preguntaDto respuesta_preguntaDto) throws Exception
    {
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();
        try
        {
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta respuesta_pregunta = dao.find(id, Respuesta_pregunta.class);
            respuesta_pregunta.set_estado( "I" );
            Respuesta_pregunta resul = dao.update(respuesta_pregunta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error inactivando una opción de respuesta de una pregunta");
        }
        return  resultado;
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
    public Pregunta_encuestaDto addLista_respuestas(@PathParam("id") long id, List<Respuesta_preguntaDto> listaRespuestas) throws Exception
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(id, Pregunta_encuesta.class);
            resultado.setId(pregunta_encuesta.get_id());
            for (Respuesta_preguntaDto respuesta_preguntaAux : listaRespuestas) {
                Respuesta_pregunta respuesta_pregunta = new Respuesta_pregunta();
                respuesta_pregunta.set_nombre( respuesta_preguntaAux.getNombre() );
                respuesta_pregunta.set_estado( "A" );
                respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);
                Respuesta_pregunta resul = dao.insert( respuesta_pregunta );
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando la lista de opciones de respuesta de una pregunta");
        }
        return  resultado;
    }

}

