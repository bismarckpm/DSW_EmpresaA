package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Producto_presentacion;

import javax.persistence.EntityManager;

public class DaoProducto_presentacion extends Dao<Producto_presentacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoProducto_presentacion( )
    {
        super( _handler );
    }
}
