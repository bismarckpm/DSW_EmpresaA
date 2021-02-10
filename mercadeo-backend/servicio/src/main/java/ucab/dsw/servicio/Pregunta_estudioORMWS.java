package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
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
    public Pregunta_estudioDto addPregunta_estudio(Pregunta_estudioDto pregunta_estudioDto ) throws Exception
    {
        Pregunta_estudioDto resultado = new Pregunta_estudioDto();
        try
        {
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
            pregunta_estudio.set_estado( pregunta_estudioDto.getEstado() );
            pregunta_estudio.set_pregunta(pregunta_estudioDto.getPregunta());
            Estudio estudio = new Estudio(pregunta_estudioDto.getEstudioDto().getId());
            pregunta_estudio.set_estudio( estudio);
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta(pregunta_estudioDto.getPreguntaEncuestaDto().getId());
            pregunta_estudio.set_preguntaEncuesta( pregunta_encuesta);
            Pregunta_estudio resul = dao.insert( pregunta_estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error asignando una pregunta a un estudio");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todas las preguntas asignadas a estudios
     *
     * @return      la lista completa de preguntas asignadas a estudios
     */
    @GET
    @Path("/showPregunta_estudio")
    public List<Pregunta_estudio> showPregunta_estudios() throws Exception{
        List<Pregunta_estudio> pregunta_estudios = null;
        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            pregunta_estudios = dao.findAll(Pregunta_estudio.class);
            System.out.println("Pregunta_estudios:");
            for (Pregunta_estudio pregunta_estudio : pregunta_estudios) {
                System.out.print(pregunta_estudio.get_id());
                System.out.print(", ");
                System.out.print(pregunta_estudio.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_estudio.get_estudio().get_id());
                System.out.print("");
                System.out.print(pregunta_estudio.get_preguntaEncuesta().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error la lista de preguntas asignadas a estudios");
        }
        return pregunta_estudios;
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

        try {
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<PreguntasResponse> preguntas = daoPregunta_estudio.listarPreguntasDeEstudio(idEstudio);

            return preguntas;
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
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<PreguntasResponse> preguntasGenerales = daoPregunta_estudio.listarPreguntasGenerales(idestudio);

            return Response.status(Response.Status.OK).entity(preguntasGenerales).build();
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

        try {
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<PreguntasResponse> preguntasRecomendadas = daoPregunta_estudio.listarPreguntasRecomendadas(idestudio);

            return preguntasRecomendadas;
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
    public Pregunta_estudioDto updatePregunta_estudio( @PathParam("id") long id , Pregunta_estudioDto pregunta_estudioDto) throws  Exception
    {
        Pregunta_estudioDto resultado = new Pregunta_estudioDto();
        try
        {
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            Pregunta_estudio pregunta_estudio = dao.find(id, Pregunta_estudio.class);
            pregunta_estudio.set_estado( pregunta_estudioDto.getEstado() );
            pregunta_estudio.set_pregunta(pregunta_estudioDto.getPregunta());
            Estudio estudio = new Estudio(pregunta_estudioDto.getEstudioDto().getId());
            pregunta_estudio.set_estudio( estudio);
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta(pregunta_estudioDto.getPreguntaEncuestaDto().getId());
            pregunta_estudio.set_preguntaEncuesta( pregunta_encuesta);
            Pregunta_estudio resul = dao.update(pregunta_estudio);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando una pregunta asignada a un estudio");
        }
        return  resultado;
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
                System.out.print(pregunta_encuestax.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando el enunciado de una pregunta");
        }
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
        return  resultado;
    }

    /**
     * Este método elimina una pregunta asignada a un estudio
     *
     * @param  "id"  id de la pregunta a ser eliminada
     */
    @DELETE
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
