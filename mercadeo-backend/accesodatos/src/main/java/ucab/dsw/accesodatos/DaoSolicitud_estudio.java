package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoSolicitud_estudio extends Dao<Solicitud_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de solicitudes de estudio pertenecientes a un cliente específico
     *
     * @param  id  id del cliente del cual se desea obtener sus solicitudes de estudio
     * @return      una lista de solicitudes de estudio pertenecientes a un cliente específico
     */
    public List<Solicitud_estudio> solicitudesCliente(Long id){
        try{
            TypedQuery<Solicitud_estudio> solicitudes = this._em.createNamedQuery( "solicitudesCliente", Solicitud_estudio.class);
            solicitudes.setParameter("id_usuario", id);
            solicitudes.getResultList();

            List<Solicitud_estudio> resultado = solicitudes.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoSolicitud_estudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
