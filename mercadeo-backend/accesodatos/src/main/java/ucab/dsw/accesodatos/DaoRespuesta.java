package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
import java.util.List;

public class DaoRespuesta extends Dao<Respuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuesta( ) throws CustomException
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    /**
     * Este método retorna una lista de respuestas hechas por encuestados relativas a una pregunta
     * de selección simple de un estudio
     *
     * @param  pregunta_estudio  pregunta de selección simple de la cual se desea obtener sus respuestas
     * @return      una lista de respuestas de encuestados a una pregunta de selección simple
     */
    public List<Respuesta> getRespuestasAPreguntaSimple(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._respuestaSimple = re._respuestaSimple AND re2._preguntaEstudio= :pregunta) ", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna un long que representa la cantidad de encuestados que respondieron a una
     * opción específica de una pregunta de selección simple de un estudio
     *
     * @param  respuesta  respuesta de la cual se quiere obtener la cantidad de encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción
     * de una pregunta de selección simple
     */
    public List<Long> contarRespuestasSimples(Respuesta respuesta){
        try{
            TypedQuery<Long> cantidad = this._em.createQuery( "SELECT count(re) FROM Respuesta re WHERE re._respuestaSimple= :respuesta AND re._preguntaEstudio._id = :pestudio", Long.class);
            cantidad.setParameter("respuesta", respuesta.get_respuestaSimple());
            cantidad.setParameter("pestudio", respuesta.get_preguntaEstudio().get_id());
            cantidad.getResultList();

            List<Long> resultado = cantidad.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de respuestas hechas por encuestados relativas a una pregunta
     * de selección múltiple de un estudio
     *
     * @param  pregunta_estudio  pregunta de selección múltiple de la cual se desea obtener sus respuestas
     * @return      una lista de respuestas de encuestados a una pregunta de selección múltiple
     */
    public List<Respuesta> getRespuestasAPreguntaMultiple(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._respuestaMultiple = re._respuestaMultiple AND re2._preguntaEstudio= :pregunta) ", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna un long que representa la cantidad de encuestados que respondieron a una
     * opción específica de una pregunta de selección múltiple de un estudio
     *
     * @param  respuesta  respuesta de la cual se quiere obtener la cantidad de encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción
     * de una pregunta de selección simple
     */
    public List<Long> contarRespuestasMultiples(Respuesta respuesta){
        try{
            TypedQuery<Long> cantidad = this._em.createQuery( "SELECT count(re) FROM Respuesta re WHERE re._respuestaMultiple= :respuesta AND re._preguntaEstudio._id = :pestudio", Long.class);
            cantidad.setParameter("respuesta", respuesta.get_respuestaMultiple());
            cantidad.setParameter("pestudio", respuesta.get_preguntaEstudio().get_id());
            cantidad.getResultList();

            List<Long> resultado = cantidad.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de respuestas hechas por encuestados relativas a una pregunta
     * de verdadero o falso de un estudio
     *
     * @param  pregunta_estudio  pregunta de verdadero o falso de la cual se desea obtener sus respuestas
     * @return      una lista de respuestas de encuestados a una pregunta de verdadero o falso
     */
    public List<Respuesta> getRespuestasAPreguntaVF(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._verdaderoFalso = re._verdaderoFalso AND re2._preguntaEstudio= :pregunta) ", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna un long que representa la cantidad de encuestados que respondieron a una
     * opción específica de una pregunta de verdadero o falso de un estudio
     *
     * @param  respuesta  respuesta de la cual se quiere obtener la cantidad de encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción
     * de una pregunta de verdadero o falso
     */
    public List<Long> contarRespuestasVF(Respuesta respuesta){
        try{
            TypedQuery<Long> cantidad = this._em.createQuery( "SELECT count(re) FROM Respuesta re WHERE re._verdaderoFalso= :respuesta AND re._preguntaEstudio._id = :pestudio", Long.class);
            cantidad.setParameter("respuesta", respuesta.get_verdaderoFalso());
            cantidad.setParameter("pestudio", respuesta.get_preguntaEstudio().get_id());
            cantidad.getResultList();

            List<Long> resultado = cantidad.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de respuestas hechas por encuestados relativas a una pregunta
     * de escala de un estudio
     *
     * @param  pregunta_estudio  pregunta de escala de la cual se desea obtener sus respuestas
     * @return      una lista de respuestas de encuestados a una pregunta de escala
     */
    public List<Respuesta> getRespuestasAPreguntaEscala(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._escala = re._escala AND re2._preguntaEstudio= :pregunta) ", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna un long que representa la cantidad de encuestados que respondieron a una
     * opción específica de una pregunta de escala de un estudio
     *
     * @param  respuesta  respuesta de la cual se quiere obtener la cantidad de encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción
     * de una pregunta de escala
     */
    public List<Long> contarRespuestasEscala(Respuesta respuesta){
        try{
            TypedQuery<Long> cantidad = this._em.createQuery( "SELECT count(re) FROM Respuesta re WHERE re._escala= :respuesta AND re._preguntaEstudio._id = :pestudio", Long.class);
            cantidad.setParameter("respuesta", respuesta.get_escala());
            cantidad.setParameter("pestudio", respuesta.get_preguntaEstudio().get_id());
            cantidad.getResultList();

            List<Long> resultado = cantidad.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de respuestas hechas por encuestados relativas a una pregunta
     * abierta de un estudio
     *
     * @param  pregunta_estudio  pregunta abierta de la cual se desea obtener sus respuestas
     * @return      una lista de respuestas de encuestados a una pregunta abierta
     */
    public List<Respuesta> getRespuestasAPreguntaAbierta(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._respuestaAbierta = re._respuestaAbierta AND re2._preguntaEstudio= :pregunta) ", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de respuestas hechas por un encuestado específico para una pregunta
     * de un estudio específico
     *
     * @param  pregunta_estudio  pregunta de la cual se desea obtener sus respuestas
     * @param  id_usuario  id del encuestado que respondió la pregunta
     * @return      una lista de respuestas de un encuestado a una pregunta de un estudio específico
     */
    public List<Respuesta> getRespuestasDeEncuestado(Pregunta_estudio pregunta_estudio, Long id_usuario){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta re WHERE re._preguntaEstudio = :pregunta and re._usuario._id = :id_usuario ", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio);
            respuestas.setParameter("id_usuario", id_usuario).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    public List<Object[]> listarPreguntaEncuesta(long id){

        String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta," +
                " pt._id as idPreguntaEstudio from Pregunta_encuesta as pe, Pregunta_estudio as pt where " +
                "pe._id = pt._preguntaEncuesta._id and pt._estudio._id =: id " +
                "ORDER BY pe._id ";
        Query query = _em.createQuery( hql);
        query.setParameter("id", id);
        List<Object[]> preguntas_respuestas = query.getResultList();

        return preguntas_respuestas;
    }

    public List<Object[]> listarRespuestaEncuesta(long id){

        String hql = "select rp._preguntaEncuesta._id as id, rp._nombre as pregunta" +
                " from Pregunta_encuesta as pe, Pregunta_estudio as pt, Respuesta_pregunta as rp where " +
                "pe._id = pt._preguntaEncuesta._id and pe._id = rp._preguntaEncuesta._id and " +
                "pt._estudio._id =: id " +
                "ORDER BY pe._id";
        Query query = _em.createQuery( hql );
        query.setParameter("id", id);
        List<Object[]> respuestas = query.getResultList();

        return respuestas;
    }
}
