package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRespuesta extends Dao<Respuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de respuestas hechas por encuestados relativas a una pregunta
     * de selección simple de un estudio
     *
     * @param  pregunta_estudio  pregunta de selección simple de la cual se desea obtener sus respuestas
     * @return      una lista de respuestas de encuestados a una pregunta de selección simple
     */
    public List<Respuesta> getRespuestasAPreguntaSimple(Pregunta_estudio pregunta_estudio){
        try{
            TypedQuery<Respuesta> respuestas = this._em.createNamedQuery( "getRespuestasAPreguntaSimple", Respuesta.class);
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
            TypedQuery<Long> cantidad = this._em.createNamedQuery( "contarRespuestasSimples", Long.class);
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
            TypedQuery<Respuesta> respuestas = this._em.createNamedQuery( "getRespuestasAPreguntaMultiple", Respuesta.class);
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
            TypedQuery<Long> cantidad = this._em.createNamedQuery( "contarRespuestasMultiples", Long.class);
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
            TypedQuery<Respuesta> respuestas = this._em.createNamedQuery( "getRespuestasAPreguntaVF", Respuesta.class);
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
            TypedQuery<Long> cantidad = this._em.createNamedQuery( "contarRespuestasVF", Long.class);
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
            TypedQuery<Respuesta> respuestas = this._em.createNamedQuery( "getRespuestasAPreguntaEscala", Respuesta.class);
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
            TypedQuery<Long> cantidad = this._em.createNamedQuery( "contarRespuestasEscala", Long.class);
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
            TypedQuery<Respuesta> respuestas = this._em.createNamedQuery( "getRespuestasAPreguntaAbierta", Respuesta.class);
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
            TypedQuery<Respuesta> respuestas = this._em.createNamedQuery( "getRespuestasDeEncuestado", Respuesta.class);
            respuestas.setParameter("pregunta", pregunta_estudio);
            respuestas.setParameter("id_usuario", id_usuario).getResultList();
            respuestas.getResultList();

            List<Respuesta> resultado = respuestas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoRespuesta( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
