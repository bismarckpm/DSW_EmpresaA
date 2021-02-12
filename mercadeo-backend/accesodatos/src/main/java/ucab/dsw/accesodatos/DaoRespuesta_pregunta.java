package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRespuesta_pregunta extends Dao<Respuesta_pregunta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de respuestas que representan las opciones que pueden
     * ser respondidas para una pregunta específica
     *
     * @param  pregunta_encuesta  pregunta de la cual se desea obtener sus opciones
     * @return      una lista de respuestas que representan las opciones de una pregunta específica
     */
    public List<Respuesta_pregunta> getRespuestasPregunta(Pregunta_encuesta pregunta_encuesta){
        try{
            TypedQuery<Respuesta_pregunta> respuestas = this._em.createQuery( "SELECT re FROM Respuesta_pregunta re WHERE re._preguntaEncuesta= :pregunta ", Respuesta_pregunta.class);
            respuestas.setParameter("pregunta", pregunta_encuesta).getResultList();
            respuestas.getResultList();

            List<Respuesta_pregunta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoRespuesta_pregunta( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}