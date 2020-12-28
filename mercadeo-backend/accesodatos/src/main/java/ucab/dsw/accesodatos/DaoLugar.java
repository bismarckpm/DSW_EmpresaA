package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoLugar extends Dao<Lugar>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public List<Lugar> getEstados(){
        try{
            TypedQuery<Lugar> lugares = this._em.createNamedQuery( "getEstados", Lugar.class);
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
