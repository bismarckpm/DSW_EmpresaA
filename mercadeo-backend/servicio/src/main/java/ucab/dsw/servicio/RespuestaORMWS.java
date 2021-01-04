package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.EstudioUsuarioResponse;
import ucab.dsw.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    @GET
    @Path("/listar/encuestados/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EstudioUsuarioResponse> getAllByRespuesta(@PathParam("id") long id) throws Exception {

        try {
            logger.info("Accediendo al servicio que me trae todos los usuarios de una encuesta");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            String hql = "select distinct r._id as idRespuesta, u._id as idUsuario, u._correo as correo," +
                    " u._nombreUsuario as nombreUsuario from Pregunta_estudio as pe, Respuesta as r, Usuario u where" +
                    " pe._estudio._id = r._preguntaEstudio._id and r._usuario._id = u._id and " +
                    "u._rol._id = 4 and pe._estudio._id =: id ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", id);

            List<Object[]> estudioUsuarioResponseList = query.getResultList();
            List<EstudioUsuarioResponse> estudioUsuarioResponseListUpdate = new ArrayList<>(estudioUsuarioResponseList.size());

            for (Object[] r : estudioUsuarioResponseList) {
                estudioUsuarioResponseListUpdate.add(new EstudioUsuarioResponse((Long)r[1], (String)r[2], (String)r[3]));
            }


            logger.info("Finalizando el servicio que me trae todos los usuarios de una encuesta");
            return estudioUsuarioResponseListUpdate;


        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @GET
    @Path("/preguntas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EncuestaResponse> obtenerPreguntaEncuesta(@PathParam("id") long id) throws Exception {

        try {
            logger.info("Accediendo al servicio de traer preguntas de encuestas");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();


            logger.info("Finalizando el servicio que me trae todos los usuarios de una encuesta");
            String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta," +
                    " pt._id as idPreguntaEstudio from Pregunta_encuesta as pe, Pregunta_estudio as pt where " +
                    "pe._id = pt._preguntaEncuesta._id and pt._estudio._id =: id " +
                    "ORDER BY pe._id ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", id);
            List<Object[]> preguntas_respuestas = query.getResultList();

            List<EncuestaResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new EncuestaResponse((long)r[0], (String)r[1], (String)r[2], (long)r[3]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @GET
    @Path("/respuestas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Respuesta_preguntaResponse> obtenerRespuestaEncuesta(@PathParam("id") long id) throws Exception {

        try {
            logger.info("Accediendo al servicio de traer respuesta de las preguntas de encuestas");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();


            logger.info("Finalizando el servicio");
            String hql = "select rp._preguntaEncuesta._id as id, rp._nombre as pregunta" +
                    " from Pregunta_encuesta as pe, Pregunta_estudio as pt, Respuesta_pregunta as rp where " +
                    "pe._id = pt._preguntaEncuesta._id and pe._id = rp._preguntaEncuesta._id and " +
                    "pt._estudio._id =: id " +
                    "ORDER BY pe._id";
            Query query = entitymanager.createQuery( hql );
            query.setParameter("id", id);
            List<Object[]> respuestas = query.getResultList();

            List<Respuesta_preguntaResponse> ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {
                ResponseListUpdate.add(new Respuesta_preguntaResponse((Long)r[0], (String)r[1]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public RespuestaDto addRespuesta(List<RespuestaDto> respuestas )
    {
        RespuestaDto resultado = new RespuestaDto();
        try
        {
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            DaoUsuario daoUsuario = new DaoUsuario();

            for (RespuestaDto respuestaDto : respuestas) {
                Respuesta respuesta = new Respuesta();
                respuesta.set_pregunta(respuestaDto.getPregunta());
                respuesta.set_estado(respuestaDto.getEstado());

                respuesta.set_escala(respuestaDto.getEscala());
                respuesta.set_respuestaAbierta(respuestaDto.getRespuertaAbierta());
                respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
                respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
                respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());

                Pregunta_estudio pregunta_estudio = daoPregunta_estudio.find(respuestaDto.getPreguntaEstudioDto().getId(), Pregunta_estudio.class);
                Usuario usuario = daoUsuario.find(respuestaDto.getUsuarioDto().getId(), Usuario.class);

                respuesta.set_usuario(usuario);
                respuesta.set_preguntaEstudio(pregunta_estudio);

                Respuesta resul = dao.insert(respuesta);
                resultado.setId( resul.get_id() );
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
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
    public List<Respuesta> showRespuestasAPreguntaSimple(@PathParam("id") long id){
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaSimple(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaSimple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return respuestas;
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
    public List<Long> contarRespuestasSimples(@PathParam("id") long id){
        List<Long> cantidad = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoRespuesta daoRespuesta= new DaoRespuesta();
            cantidad = dao.contarRespuestasSimples(daoRespuesta.find(id, Respuesta.class));
            System.out.println("Cantidad:");
            for (Long numero : cantidad) {
                System.out.print(numero);
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return cantidad;
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
    public List<Respuesta> showRespuestasAPreguntaMultiple(@PathParam("id") long id){
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaMultiple(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return respuestas;
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
    public List<Long> contarRespuestasMultiples(@PathParam("id") long id){
        List<Long> cantidad = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoRespuesta daoRespuesta= new DaoRespuesta();
            cantidad = dao.contarRespuestasMultiples(daoRespuesta.find(id, Respuesta.class));
            System.out.println("Cantidad:");
            for (Long numero : cantidad) {
                System.out.print(numero);
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return cantidad;
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
    public List<Respuesta> showRespuestasAPreguntaVF(@PathParam("id") long id){
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaVF(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return respuestas;
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
    public List<Long> contarRespuestasVF(@PathParam("id") long id){
        List<Long> cantidad = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoRespuesta daoRespuesta= new DaoRespuesta();
            cantidad = dao.contarRespuestasVF(daoRespuesta.find(id, Respuesta.class));
            System.out.println("Cantidad:");
            for (Long numero : cantidad) {
                System.out.print(numero);
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return cantidad;
    }

    /**
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta abierta
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta abierta de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaAbierta/{id}")
    public List<Respuesta> showRespuestasAPreguntaAbierta(@PathParam("id") long id){
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaAbierta(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return respuestas;
    }


}
