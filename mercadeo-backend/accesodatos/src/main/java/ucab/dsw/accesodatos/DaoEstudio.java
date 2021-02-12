package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
import java.util.List;

public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    /**
     * Este método retorna una lista de estudios cuyas características coinciden
     * con las de un estudio que ha sido solicitado, de manera tal que sea
     * recomendado como opción que satisface con dicha solicitud
     *
     * @param  id  id de la solicitud de estudio para la que se desean obtener recomendaciones
     * @return      una lista de estudios recomendados
     */
    public List<Estudio> obtenerRecomendaciones(long id){
        TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT es FROM Estudio es, Solicitud_estudio sent, Solicitud_estudio scom WHERE sent._id = :id_solicitud AND es._solicitudEstudio._id = scom._id AND sent._conCuantasPersonasVive = scom._conCuantasPersonasVive AND sent._edadMaximaPoblacion = scom._edadMaximaPoblacion AND sent._edadMinimaPoblacion = scom._edadMinimaPoblacion AND sent._nivelEconomico = scom._nivelEconomico AND sent._ocupacion = scom._ocupacion AND sent._producto._subcategoria = scom._producto._subcategoria AND sent._generoPoblacional = scom._generoPoblacional AND sent._disponibilidadEnLinea = scom._disponibilidadEnLinea", Estudio.class);
        estudios.setParameter("id_solicitud", id);
        estudios.getResultList();

        List<Estudio> resultado = estudios.getResultList();
        return resultado;

    }

    /**
     * Este método retorna una lista de estudios asignados a un analista
     *
     * @param  id  id del analista del cual se quieren obtener sus estudios asignados
     * @return      una lista de estudios asignados a un analista
     */
    public List<Estudio> getEstudiosUsuario(long id){

        TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT es FROM Estudio es WHERE es._usuario._id = :id_usuario", Estudio.class);
        estudios.setParameter("id_usuario", id);
        estudios.getResultList();

        List<Estudio> resultado = estudios.getResultList();
        return resultado;

    }

    /**
     * Este método retorna una lista de estudios solicitados por un cliente
     *
     * @param  id  id del cliente del cual se quieren obtener sus estudios
     * @return      una lista de estudios pertenecientes a un cliente
     */
    public List<Estudio> getEstudiosCliente(long id){

        TypedQuery<Estudio> estudios = this._em.createQuery("SELECT es FROM Estudio es, Solicitud_estudio  se WHERE se._usuario._id = :id_usuario AND es._solicitudEstudio._id = se._id", Estudio.class);
        estudios.setParameter("id_usuario", id);
        estudios.getResultList();

        List<Estudio> resultado = estudios.getResultList();
        return resultado;

    }

    /**
     * Este método retorna la cantidad de encuestados que participaron en un estudio
     *
     * @param  id  id del estudio del cual se quiere obtener la cantidad de participantes
     * @return      un long que representa la cantidad de encuestados que participaron en un estudio
     */
    public Long contarParticipantes(long id){

        TypedQuery<Long> participantes = this._em.createQuery( "SELECT count(distinct re._usuario) FROM Respuesta re, Pregunta_estudio pe, Estudio es WHERE re._preguntaEstudio._id = pe._id AND pe._estudio._id = :id_estudio", Long.class);
        participantes.setParameter("id_estudio", id).getSingleResult();

        Long resultado = participantes.getSingleResult();
        return resultado;

    }

    /**
     * Este método retorna una lista de estudios que han sido respondidos por un encuestado
     *
     * @param  id  id del usuario del cual se quiere obtener sus estudios respondidos
     * @return      una lista de estudios a los que ha respondido un encuestado
     */
    public List<Estudio> getEstudiosRespondidosEncuestado(long id){

        TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT distinct (es) FROM Estudio es, Respuesta re, Pregunta_estudio pe WHERE re._usuario._id = :id_usuario AND re._preguntaEstudio._id = pe._id AND pe._estudio._id = es._id", Estudio.class);
        estudios.setParameter("id_usuario", id);
        estudios.getResultList();

        List<Estudio> resultado = estudios.getResultList();
        return resultado;

    }


    public List<Respuesta> validarParticipacion(long id_usuario, long id_estudio){

        TypedQuery<Respuesta> estudios = this._em.createQuery( "SELECT re FROM Respuesta re, Pregunta_estudio pe WHERE re._preguntaEstudio._id = pe._id and re._usuario._id = :id_usuario and pe._estudio._id = :id_estudio", Respuesta.class);
        estudios.setParameter("id_usuario", id_usuario);
        estudios.setParameter("id_estudio", id_estudio);
        estudios.getResultList();

        List<Respuesta> resultado = estudios.getResultList();
        return resultado;

    }


    public List<Respuesta> validarContestado(long id_estudio){

        TypedQuery<Respuesta> estudios = this._em.createQuery( "SELECT re FROM Respuesta re, Pregunta_estudio pe WHERE re._preguntaEstudio._id = pe._id and pe._estudio._id = :id_estudio", Respuesta.class);
        estudios.setParameter("id_estudio", id_estudio);
        estudios.getResultList();

        List<Respuesta> resultado = estudios.getResultList();
        return resultado;

    }

    public List<Estudio> getEstudioPorSolicitud(long id_solicitud){

        TypedQuery<Estudio> estudio = this._em.createQuery( "SELECT es FROM Estudio es WHERE  es._solicitudEstudio._id = :id_solicitud", Estudio.class);
        estudio.setParameter("id_solicitud", id_solicitud);
        estudio.getResultList();

        List<Estudio> resultado = estudio.getResultList();
        return resultado;

    }

    /**
     * Este método retorna una lista de estudios que han sido respondidos por completo por un encuestado
     *
     * @param  id  id del usuario del cual se quiere obtener sus estudios respondidos por completo
     * @return      una lista de estudios a los que ha respondido un encuestado
     */
    public List<Estudio> getEstudiosRespondidosCompletos(long id){

        TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT distinct (es) FROM Estudio es, Respuesta re, Pregunta_estudio pe WHERE (SELECT count(pt._id) FROM Pregunta_estudio as pt WHERE pt._estudio._id = es._id)=(SELECT count(DISTINCT  r._preguntaEstudio._id) FROM Respuesta as r, Pregunta_estudio as pt WHERE pt._id = r._preguntaEstudio._id and r._usuario._id = :id_usuario and pt._estudio._id = es._id)", Estudio.class);
        estudios.setParameter("id_usuario", id);
        estudios.getResultList();

        List<Estudio> resultado = estudios.getResultList();
        return resultado;

    }


}
