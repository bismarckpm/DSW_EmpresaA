package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.EstudioUsuarioResponse;
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
    private DaoRespuesta daoRespuesta = new DaoRespuesta();
    private DaoPregunta_estudio daoPreguntaEstudio = new DaoPregunta_estudio();


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
    @Path("/Encuesta/{id}/{iduser}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EncuestaResponse> obtenerPreguntaEncuesta(@PathParam("id") long id, @PathParam("iduser") long iduser) throws Exception {

        try {
            logger.info("Accediendo al servicio de traer preguntas de encuestas");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();


            logger.info("Finalizando el servicio que me trae todos los usuarios de una encuesta");
            String hql = "select pe._id as id, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta , rp._nombre as pregunta" +
                    " from Pregunta_encuesta as pe, Pregunta_estudio as pt, Respuesta_pregunta as rp where " +
                    "pe._id = pt._preguntaEncuesta._id and pe._id = rp._preguntaEncuesta._id and " +
                    "pt._id not in ( ( select r._preguntaEstudio._id from Respuesta as r where " +
                    "r._preguntaEstudio._id = pt._id and pt._estudio._id =: id and r._usuario._id =: iduser) ) " +
                    "ORDER BY pe._id ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", id).setParameter("iduser", iduser);
            List<Object[]> preguntas_respuestas = query.getResultList();

            List<EncuestaResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new EncuestaResponse((Long)r[0], (String)r[1], (String)r[2], (String)r[3]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @POST
    @Path( "/agregar/{idestudio}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public RespuestaDto addRespuesta(@PathParam("idestudio") long idestudio, List<RespuestaDto> respuestas )
    {
        RespuestaDto resultado = new RespuestaDto();
        try
        {
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            DaoUsuario daoUsuario = new DaoUsuario();

            for (RespuestaDto respuestaDto : respuestas) {
                Respuesta respuesta = new Respuesta();
                respuesta.set_estatus(respuestaDto.getEstatus());
                respuesta.set_pregunta(respuestaDto.getPregunta());
                respuesta.set_estado(respuestaDto.getEstado());

                respuesta.set_escala(respuestaDto.getEscala());
                respuesta.set_respuestaAbierta(respuestaDto.getRespuertaAbierta());
                respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
                respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
                respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());

                Pregunta_estudio pregunta_estudio = daoPregunta_estudio.find(idestudio, Pregunta_estudio.class);
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
                System.out.print(respuesta.get_estatus());
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
                System.out.print(respuesta.get_estatus());
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
                System.out.print(respuesta.get_estatus());
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
                System.out.print(respuesta.get_estatus());
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
