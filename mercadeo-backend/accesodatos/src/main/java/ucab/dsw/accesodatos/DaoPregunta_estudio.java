package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_estudio;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
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

    public List<Object[]> listarPreguntasDeEstudio(long idEstudio){

        String hql = "select pt._id as idPreguntaEncuesta, pt._pregunta as pregunta , pe._tipoPregunta as tipoPregunta" +
                " from Pregunta_encuesta as pe, Pregunta_estudio as pt WHERE " +
                "pe._id = pt._preguntaEncuesta._id and pt._estudio._id =: id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", idEstudio);
        List<Object[]> preguntas = query.getResultList();

        return preguntas;
    }

    public List<Object[]> listarPreguntasGenerales(long idestudio){

        String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta" +
                " from Pregunta_encuesta as pe where " +
                "pe._id not in (select pt._preguntaEncuesta._id from Pregunta_estudio as pt where pt._estudio._id =: id) " +
                "ORDER BY pe._id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", idestudio);
        List<Object[]> preguntasGenerales = query.getResultList();

        return preguntasGenerales;
    }

    public List<Object[]> listarPreguntasRecomendadas(long idestudio){

        String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta" +
                " from Pregunta_encuesta as pe where " +
                "pe._id not in (select pt._preguntaEncuesta._id from Pregunta_estudio as pt where pt._estudio._id =: id) and " +
                "pe._subcategoria._id = (select pr._subcategoria._id from Estudio as e, Solicitud_estudio as se, " +
                "Producto as pr where e._id =: id and e._solicitudEstudio._id = se._id and se._producto._id = pr._id) " +
                "ORDER BY pe._id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", idestudio);
        List<Object[]> preguntasRecomendadas = query.getResultList();

        return preguntasRecomendadas;
    }

}
