package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Solicitud_estudio;

import javax.persistence.EntityManager;

public class DaoSolicitud_estudio extends Dao<Solicitud_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoSolicitud_estudio( )
    {
        super( _handler );
    }
}
