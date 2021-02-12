package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


import javax.persistence.EntityManager;

public class DaoPregunta_encuesta extends Dao<Pregunta_encuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de preguntas que aceptan opciones personalizadas, es decir,
     * preguntas de selección simple o selección múltiple
     *
     * @return      una lista de preguntas que aceptan opciones personalizadas
     */
    public List<Pregunta_encuesta> getConOpciones(){
        try{
            TypedQuery <Pregunta_encuesta> preguntas = this._em.createQuery( "SELECT pe FROM Pregunta_encuesta pe WHERE pe._tipoPregunta = 'Seleccion simple' OR pe._tipoPregunta = 'Seleccion multiple'  ", Pregunta_encuesta.class);
            preguntas.getResultList();

            List<Pregunta_encuesta> resultado = preguntas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una pregunta para tomar de ella su enunciado posteriormente
     *
     * @param  pregunta_estudio  pregunta_estudio de la cual se desea obtener su enunciado
     * @return      una pregunta_encuesta con todos sus datos
     */
    public List<Pregunta_encuesta> getEnunciadoPregunta(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery <Pregunta_encuesta> pregunta = this._em.createQuery( "SELECT penc FROM Pregunta_encuesta penc, Pregunta_estudio pest WHERE penc._id = :fk_encuesta  ", Pregunta_encuesta.class);
            pregunta.setParameter("fk_encuesta", pregunta_estudio.get_preguntaEncuesta().get_id()).getResultList();
            pregunta.getResultList();

            List<Pregunta_encuesta> resultado = pregunta.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoPregunta_encuesta( ) throws CustomException
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
