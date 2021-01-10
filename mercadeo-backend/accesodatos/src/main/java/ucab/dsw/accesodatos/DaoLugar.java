package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoLugar extends Dao<Lugar>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna la lista de lugares de tipo Estado
     *
     * @return      una lista de lugares de tipo Estado
     */
    public List<Lugar> getEstados(){
        try{
            TypedQuery<Lugar> lugares = this._em.createQuery( "SELECT lu FROM Lugar lu WHERE lu._tipo = 'Estado' ", Lugar.class);
            lugares.getResultList();

            List<Lugar> resultado = lugares.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Este método retorna una lista de lugares que representan las regiones de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio de la cual se quiere obtener sus regiones de estudio
     * @return      una lista de lugares que representan las regiones de estudio de una solicitud de estudio
     */
    public List<Lugar> getRegionesDeSolicitud(long id){
        try{
            TypedQuery<Lugar> lugares = this._em.createQuery( "SELECT lu FROM Lugar lu, Region_estudio re WHERE re._lugar = lu AND re._solicitudEstudio._id = :id_solicitud ", Lugar.class);
            lugares.setParameter("id_solicitud", id).getResultList();
            lugares.getResultList();

            List<Lugar> resultado = lugares.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoLugar( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
