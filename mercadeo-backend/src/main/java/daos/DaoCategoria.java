package daos;

import entidades.Categoria;

import javax.persistence.EntityManager;

public class DaoCategoria extends Dao<Categoria>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoCategoria( )
    {
        super( _handler );
    }
}
