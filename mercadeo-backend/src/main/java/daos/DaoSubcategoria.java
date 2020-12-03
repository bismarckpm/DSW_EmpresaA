package daos;

import entidades.Subcategoria;

import javax.persistence.EntityManager;

public class DaoSubcategoria extends Dao<Subcategoria>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoSubcategoria( )
    {
        super( _handler );
    }
}
