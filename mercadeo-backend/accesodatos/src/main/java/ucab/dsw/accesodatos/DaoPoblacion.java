package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Poblacion;

import javax.persistence.EntityManager;

public class DaoPoblacion extends Dao<Poblacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPoblacion( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

}
