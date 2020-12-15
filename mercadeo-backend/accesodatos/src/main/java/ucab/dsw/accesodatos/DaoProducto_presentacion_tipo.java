package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Producto_presentacion_tipo;

import javax.persistence.EntityManager;

public class DaoProducto_presentacion_tipo extends Dao<Producto_presentacion_tipo>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoProducto_presentacion_tipo( )
    {
        super( _handler );
    }
}
