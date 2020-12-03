package daos;

import entidades.Pregunta_encuesta;

import javax.persistence.EntityManager;

public class DaoPregunta_encuesta extends Dao<Pregunta_encuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoPregunta_encuesta( )
    {
        super( _handler );
    }
}
