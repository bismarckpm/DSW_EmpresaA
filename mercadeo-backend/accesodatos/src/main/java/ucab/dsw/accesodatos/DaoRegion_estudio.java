package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRegion_estudio extends Dao<Region_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de regiones de estudio de una solicitud de estudio,
     * para ser posteriormente actualizadas
     *
     * @param  id  id de la solicitud de estudio de la cual se desea obtener sus regiones
     * @return      una lista de regiones de estudio pertenecientes a una solicitud de estudio
     */
    public List<Region_estudio> getRegionesActualizar(long id){
        try{
            TypedQuery<Region_estudio> regiones = this._em.createQuery( "SELECT re FROM Region_estudio re WHERE re._solicitudEstudio._id = :id_solicitud ", Region_estudio.class);
            regiones.setParameter("id_solicitud", id).getResultList();
            regiones.getResultList();

            List<Region_estudio> resultado = regiones.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoRegion_estudio( ) throws CustomException
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
