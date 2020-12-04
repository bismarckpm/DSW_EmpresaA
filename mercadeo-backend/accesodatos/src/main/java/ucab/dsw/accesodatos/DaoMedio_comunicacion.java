package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Medio_comunicacion;

import javax.persistence.EntityManager;

public class DaoMedio_comunicacion extends Dao<Medio_comunicacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoMedio_comunicacion( )
    {
        super( _handler );
    }
}
