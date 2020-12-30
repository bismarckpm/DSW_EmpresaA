package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public List<Estudio> obtenerRecomendaciones(long id){
        try{
            TypedQuery<Estudio> estudios = this._em.createNamedQuery( "obtenerRecomendaciones", Estudio.class);
            estudios.setParameter("id_solicitud", id);
            estudios.getResultList();

            List<Estudio> resultado = estudios.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    public List<Estudio> getEstudiosUsuario(long id){
        try{
            TypedQuery<Estudio> estudios = this._em.createNamedQuery( "getEstudiosUsuario", Estudio.class);
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
