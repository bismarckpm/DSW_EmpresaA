package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Region_estudio;

import javax.persistence.EntityManager;

public class DaoRegion_estudio extends Dao<Region_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoRegion_estudio( )
    {
        super( _handler );
    }
}
