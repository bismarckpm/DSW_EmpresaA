package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de estudios cuyas características coinciden
     * con las de un estudio que ha sido solicitado, de manera tal que sea
     * recomendado como opción que satisface con dicha solicitud
     *
     * @param  id  id de la solicitud de estudio para la que se desean obtener recomendaciones
     * @return      una lista de estudios recomendados
     */
    public List<Estudio> obtenerRecomendaciones(long id){
        try{
            TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT es FROM Estudio es, Solicitud_estudio sent, Solicitud_estudio scom WHERE sent._id = :id_solicitud AND es._solicitudEstudio._id = scom._id AND sent._conCuantasPersonasVive = scom._conCuantasPersonasVive AND sent._edadMaximaPoblacion = scom._edadMaximaPoblacion AND sent._edadMinimaPoblacion = scom._edadMinimaPoblacion AND sent._nivelEconomico = scom._nivelEconomico AND sent._ocupacion = scom._ocupacion AND sent._producto._subcategoria = scom._producto._subcategoria AND sent._generoPoblacional = scom._generoPoblacional AND sent._disponibilidadEnLinea = scom._disponibilidadEnLinea", Estudio.class);
            estudios.setParameter("id_solicitud", id);
            estudios.getResultList();

            List<Estudio> resultado = estudios.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de estudios asignados a un analista
     *
     * @param  id  id del analista del cual se quieren obtener sus estudios asignados
     * @return      una lista de estudios asignados a un analista
     */
    public List<Estudio> getEstudiosUsuario(long id){
        try{
            TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT es FROM Estudio es WHERE es._usuario._id = :id_usuario", Estudio.class);
            estudios.setParameter("id_usuario", id);
            estudios.getResultList();

            List<Estudio> resultado = estudios.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de estudios solicitados por un cliente
     *
     * @param  id  id del cliente del cual se quieren obtener sus estudios
     * @return      una lista de estudios pertenecientes a un cliente
     */
    public List<Estudio> getEstudiosCliente(long id){
        try{
            TypedQuery<Estudio> estudios = this._em.createQuery("SELECT es FROM Estudio es, Solicitud_estudio  se WHERE se._usuario._id = :id_usuario AND es._solicitudEstudio._id = se._id", Estudio.class);
            estudios.setParameter("id_usuario", id);
            estudios.getResultList();

            List<Estudio> resultado = estudios.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna la cantidad de encuestados que participaron en un estudio
     *
     * @param  id  id del estudio del cual se quiere obtener la cantidad de participantes
     * @return      un long que representa la cantidad de encuestados que participaron en un estudio
     */
    public Long contarParticipantes(long id){
        try{
            TypedQuery<Long> participantes = this._em.createQuery( "SELECT count(distinct re._usuario) FROM Respuesta re, Pregunta_estudio pe, Estudio es WHERE re._preguntaEstudio._id = pe._id AND pe._estudio._id = :id_estudio", Long.class);
            participantes.setParameter("id_estudio", id).getSingleResult();

            Long resultado = participantes.getSingleResult();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de estudios que han sido respondidos por un encuestado
     *
     * @param  id  id del usuario del cual se quiere obtener sus estudios respondidos
     * @return      una lista de estudios a los que ha respondido un encuestado
     */
    public List<Estudio> getEstudiosRespondidosEncuestado(long id){
        try{
            TypedQuery<Estudio> estudios = this._em.createQuery( "SELECT distinct (es) FROM Estudio es, Respuesta re, Pregunta_estudio pe WHERE re._usuario._id = :id_usuario AND re._preguntaEstudio._id = pe._id AND pe._estudio._id = es._id", Estudio.class);
            estudios.setParameter("id_usuario", id);
            estudios.getResultList();

            List<Estudio> resultado = estudios.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoEstudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
