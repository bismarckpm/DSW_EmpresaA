package daos;

import entidades.Respuesta_pregunta;

import javax.persistence.EntityManager;

public class DaoRespuesta_pregunta extends Dao<Respuesta_pregunta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoRespuesta_pregunta( )
    {
        super( _handler );
    }
}
