package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Dato_usuario;

import javax.persistence.EntityManager;

public class DaoDato_usuario extends Dao<Dato_usuario>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoDato_usuario( )
    {
        super( _handler );
    }
}
