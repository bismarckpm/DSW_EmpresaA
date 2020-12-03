package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.EncuestadoResponse;
import ucab.dsw.Response.EstudioUsuarioResponse;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.entidades.Respuesta;

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

}
