package ucab.dsw.accesodatos;


import ucab.dsw.entidades.Producto_tipo;

import javax.persistence.EntityManager;

public class DaoProducto_tipo extends Dao<Producto_tipo>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoProducto_tipo( )
    {
        super( _handler );
    }

}
