package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Nivel_academico;

import javax.persistence.EntityManager;

public class DaoNivel_academico extends Dao<Nivel_academico>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoNivel_academico( )
    {
        super( _handler );
    }
}
