package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoRegion_estudio extends Dao<Region_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public List<Region_estudio> getRegionesActualizar(long id){
        try{
            TypedQuery<Region_estudio> regiones = this._em.createNamedQuery( "getRegionesActualizar", Region_estudio.class);
            regiones.setParameter("id_solicitud", id).getResultList();
            regiones.getResultList();

            List<Region_estudio> resultado = regiones.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoRegion_estudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
