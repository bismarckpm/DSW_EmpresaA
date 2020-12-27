package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.EstudioUsuarioResponse;
import ucab.dsw.Response.UsuarioRespondieronEstudioResponse;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
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
    public List<EncuestaResponse> obtenerPreguntaEncuesta(@PathParam("id") long id,@PathParam("iduser") long iduser) throws Exception {

        try {
            logger.info("Accediendo al servicio de traer preguntas de encuestas");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();


            logger.info("Finalizando el servicio que me trae todos los usuarios de una encuesta");
            String hql = "select pe._id as id, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta " +
                    " from Pregunta_encuesta as pe, Pregunta_estudio as pt where pe._id = pt._preguntaEncuesta._id and " +
                    "pt._id not in ( ( select r._preguntaEstudio._id from Respuesta as r where " +
                    "r._preguntaEstudio._id = pt._id and pt._estudio._id =: id and r._usuario._id =: iduser) ) ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", id).setParameter("iduser", iduser);
            List<Object[]> preguntas_respuestas = query.getResultList();

            List<EncuestaResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                /* String hql1 = "select rp._nombre as respuesta" +
                        " from Respuesta_pregunta as rp where rp._preguntaEncuesta._id =: var  ";
                Query query1 = entitymanager.createQuery( hql1);
                query1.setParameter("var", (long)r[1]);
                List<String> preguntas = query.getResultList();*/

                    ResponseListUpdate.add(new EncuestaResponse((String)r[1], (String)r[2]));

            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

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

    @GET
    @Path("/listar/encuestados-resuelto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<UsuarioRespondieronEstudioResponse> getAllByUserReponse(@PathParam("id") long id) throws Exception {

        try {
            logger.info("Accediendo al servicio que me trae todos los usuarios que respondieron la encuesta ");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            String hql = "select distinct e.codigo, u.nombreUsuario, e.nombre, e.fechaInicio , e.fechaFin from  " +
                    "mercadeoucab.estudio as e , mercadeoucab.pregunta_estudio as pe, mercadeoucab.respuesta as r " +
                    "INNER JOIN mercadeoucab.usuario as u ON u.codigo = r.fk_usuario where  " +
                    "e.codigo =  pe.fk_estudio and r.fk_preguntaEstudio = pe.codigo and e.estado = 'A' and e.fk_usuario=?1" ;

            Query query = entitymanager.createNativeQuery(hql);
            query.setParameter(1, id);
            List<Object[]> objects = query.getResultList();
            List<UsuarioRespondieronEstudioResponse> usuarioRespondieronEstudioResponseList = new ArrayList<>();

            for (Object[] r : objects) {

                usuarioRespondieronEstudioResponseList.add(new UsuarioRespondieronEstudioResponse((String)r[1], (String)r[2],formatDateToString((Date)r[3]), formatDateToString((Date)r[4])));

            }


            return usuarioRespondieronEstudioResponseList;

        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    private String formatDateToString(Date date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dateUpdate = sdf.format(date);

        return dateUpdate;
    }

}
