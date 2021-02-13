package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
import logica.comando.pregunta_encuesta.*;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.PreguntaEncuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/pregunta_encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_encuestaORMWS {

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
    public Response addPregunta_encuesta(Pregunta_encuestaDto pregunta_encuestaDto)
    {
        JsonObject resultado;
        try
        {
            AddPregunta_encuestaComando comando = Fabrica.crearComandoConEntidad(AddPregunta_encuestaComando.class, PreguntaEncuestaMapper.mapDtoToEntityInsert(pregunta_encuestaDto));
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
     * Este método retorna la lista con todas las preguntas registradas
     *
     * @return      la lista completa de preguntas registradas
     */
    @GET
    @Path("/show")
    public Response showPregunta_encuestas(){
        JsonObject resul;
        try {
            BuscarPregunta_encuestaComando comando= Fabrica.crear(BuscarPregunta_encuestaComando.class);
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
     * Este método actualiza una pregunta específica
     *
     * @param  id  id de la pregunta que se va a actualizar
     * @param  pregunta_encuestaDto  pregunta a ser actualizado
     * @return      la pregunta_enceustaDto que ha sido actualizada
     */
    @PUT
    @Path( "/update/{id}" )
    public Response updatePregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto)
    {
        JsonObject resultado;
        try
        {
            EditPregunta_encuestaComando comando= Fabrica.crearComandoConEntidad(EditPregunta_encuestaComando.class, PreguntaEncuestaMapper.mapDtoToEntityUpdate(id,pregunta_encuestaDto));
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
     * Este método consulta una pregunta específica
     *
     * @param  id  id de la pregunta a ser consultada
     * @return      la pregunta completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarPregunta_encuesta(@PathParam("id") long id){
        JsonObject resultado;
        try {
            ConsultarPregunta_encuestaComando comando=Fabrica.crearComandoConId(ConsultarPregunta_encuestaComando.class,id);
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
     * Este método incativa una pregunta_encuesta
     *
     * @param  id  id de la pregunta que se desea incativar
     * @param  pregunta_encuestaDto pregunta completa que se desea incativar
     * @return      la pregunta_encuestaDto que ha sido inactivada, con su estado actualizado
     */
    @PUT
    @Path( "/inactivar/{id}" )
    public Response incativarPregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto) throws Exception
    {
        ResponseDto resultado;
        try
        {
            InactivarComando comando= Fabrica.crearComandoConId(InactivarComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error inactivando una pregunta");
        }
    }

    /**
     * Este método retorna la lista de preguntas que tienen opciones personalizadas, es decir,
     * las preguntas de selección simple y de selección múltiple
     *
     * @return      la lista de preguntas que tienen opciones personalizadas
     */
    @GET
    @Path("/showConOpciones")
    public Response showPregunta_encuestas_con_opciones() throws Exception{
        ResponseDto resultado;
        try{
            ObtenerOpcionesComando comando= Fabrica.crear(ObtenerOpcionesComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas que poseen opciones personalizadas");
        }
    }
}
