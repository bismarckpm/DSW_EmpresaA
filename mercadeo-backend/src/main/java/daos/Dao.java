package daos;
/*
import entidades.EntidadBase;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Dao<T>
{
    private DaoHandler _daoHandler;
    private EntityManager _em;

    public Dao()
    {
    }



    public Dao( DaoHandler daoHandler )
    {
        _daoHandler = daoHandler;
    }



    public T insert( T entity )
    {
        _em = _daoHandler.getSession();

        try
        {
            _daoHandler.beginTransaction();
            _em.persist( entity );
            _em.flush();
            _daoHandler.finishTransaction();
        }
        catch ( Exception e )
        {
            throw  e;
        }

        return entity;
    }


    public T update( T entity )
    {
        _em = _daoHandler.getSession();
        try
        {
            _daoHandler.beginTransaction();
            _em.merge( entity );
            _em.flush();
            _daoHandler.finishTransaction();

        }
        catch ( Exception e )
        {
            throw  e;
        }
        return entity;
    }


    public T delete( T entity )
    {
        _em = _daoHandler.getSession();
        try
        {
            _daoHandler.beginTransaction();
            _em.remove( entity );
            _em.flush();
            _daoHandler.finishTransaction();

        }
        catch ( Exception e )
        {
            throw e;
        }
        return entity;
    }


    public List<T> findAll( Class<T> type )
    {
        _em = _daoHandler.getSession();

        final CriteriaBuilder criteriaBuilder = _em.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery( type );
        final Root<T> root = criteriaQuery.from( type );
        final List<T> list;

        try
        {
            criteriaQuery.select( root );

            list = _em.createQuery( criteriaQuery ).getResultList();

        }
        catch ( Exception e )
        {
            throw e;
        }

        return list;
    }


    public T find( Long id, Class<T> type )
    {
        _em = _daoHandler.getSession();
        final T entity;

        try
        {
            final EntidadBase base = ( EntidadBase ) _em.find( type, id );
            base.get_id();
            entity = ( T ) base;
        }
        catch ( Exception e )
        {
            throw e;
        }
        return entity;
    }


    public DaoHandler getDaoHandler()
    {
        return _daoHandler;
    }
}


 */

