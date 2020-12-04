package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Privilegio;

import javax.persistence.EntityManager;

public class DaoPrivilegio extends Dao<Privilegio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoPrivilegio( )
    {
        super( _handler );
    }
}
