package daos;

import entidades.Ocupacion;

import javax.persistence.EntityManager;

public class DaoOcupacion extends Dao<Ocupacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoOcupacion( )
    {
        super( _handler );
    }
}
