package daos;

import entidades.Rol_privilegio;

import javax.persistence.EntityManager;

public class DaoRol_privilegio extends Dao<Rol_privilegio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoRol_privilegio( )
    {
        super( _handler );
    }
}
