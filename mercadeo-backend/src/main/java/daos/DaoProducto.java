package daos;

import entidades.Producto;

import javax.persistence.EntityManager;

public class DaoProducto extends Dao<Producto>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoProducto( )
    {
        super( _handler );
    }
}
