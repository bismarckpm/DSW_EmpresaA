package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.EstudioUsuarioResponse;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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



}
