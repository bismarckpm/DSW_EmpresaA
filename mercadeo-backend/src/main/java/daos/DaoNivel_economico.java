package daos;

import entidades.Nivel_economico;

import javax.persistence.EntityManager;

public class DaoNivel_economico extends Dao<Nivel_economico>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoNivel_economico( )
    {
        super( _handler );
    }
}
