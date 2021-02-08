package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;

import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

public class DaoPregunta_estudio extends Dao<Pregunta_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPregunta_estudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    /**
     * Este método retorna una lista de preguntas asignadas a un estudio específico
     *
     * @param  estudio  estudio del cual se desean obtener sus preguntas
     * @return      una lista de preguntas asignadas a un estudio específico
     */
    public List<Pregunta_estudio> getPreguntasEstudio(Estudio estudio){
        try{
            TypedQuery<Pregunta_estudio> preguntas = this._em.createQuery( "SELECT pe FROM Pregunta_estudio pe WHERE pe._estudio= :estudio ", Pregunta_estudio.class);
            preguntas.setParameter("estudio", estudio).getResultList();
            preguntas.getResultList();

            List<Pregunta_estudio> resultado = preguntas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    public List<PreguntasResponse> listarPreguntasDeEstudio(long idEstudio){

        String hql = "select pe._id as idPreguntaEncuesta, pt._pregunta as pregunta , pe._tipoPregunta as tipoPregunta, su._nombre as subcategoria, " +
                "pt._id as idPt from Pregunta_encuesta as pe, Pregunta_estudio as pt, Subcategoria as su where su._id = pe._subcategoria._id " +
                "and pe._id = pt._preguntaEncuesta._id and pt._estudio._id =: id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", idEstudio);
        List<Object[]> preguntas = query.getResultList();

        List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntas.size());

        for (Object[] r : preguntas) {
            ResponseListUpdate.add(new PreguntasResponse((Long)r[0], (String)r[1], (String)r[2], (String)r[3], (Long)r[4]));
        }

        return ResponseListUpdate;
    }

    public List<PreguntasResponse> listarPreguntasGenerales(long idestudio){

        String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta," +
                " su._nombre as subcategoria, su._id as idsu from Pregunta_encuesta as pe, Subcategoria as su where su._id = pe._subcategoria._id" +
                " and pe._id not in (select pt._preguntaEncuesta._id from Pregunta_estudio as pt where pt._estudio._id =: id) " +
                "ORDER BY pe._id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", idestudio);
        List<Object[]> preguntasGenerales = query.getResultList();

        List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntasGenerales.size());

        for (Object[] r : preguntasGenerales) {
            ResponseListUpdate.add(new PreguntasResponse((Long)r[0], (String)r[1], (String)r[2], (String)r[3], (Long)r[4]));
        }

        return ResponseListUpdate;
    }

    public List<PreguntasResponse> listarPreguntasRecomendadas(long idestudio){

        String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta, " +
                "su._nombre as subcategoria, su._id as id from Pregunta_encuesta as pe, Subcategoria as su where su._id = pe._subcategoria._id" +
                " and pe._id not in (select pt._preguntaEncuesta._id from Pregunta_estudio as pt where pt._estudio._id =: id) and " +
                "pe._subcategoria._id = (select pr._subcategoria._id from Estudio as e, Solicitud_estudio as se, " +
                "Producto as pr where e._id =: id and e._solicitudEstudio._id = se._id and se._producto._id = pr._id) " +
                "ORDER BY pe._id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", idestudio);
        List<Object[]> preguntasRecomendadas = query.getResultList();

        List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntasRecomendadas.size());

        for (Object[] r : preguntasRecomendadas) {
            ResponseListUpdate.add(new PreguntasResponse((Long)r[0], (String)r[1], (String)r[2], (String)r[3], (Long)r[4]));
        }

        return ResponseListUpdate;
    }

}
