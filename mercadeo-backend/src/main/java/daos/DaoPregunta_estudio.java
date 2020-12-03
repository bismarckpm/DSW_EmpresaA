package daos;

import entidades.Pregunta_estudio;

import javax.persistence.EntityManager;

public class DaoPregunta_estudio extends Dao<Pregunta_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoPregunta_estudio( )
    {
        super( _handler );
    }
}
