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


    public DaoRespuesta( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
