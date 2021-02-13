package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.CustomException;

import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import java.util.List;

public class DaoHijo extends Dao<Hijo>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoHijo( ) throws CustomException
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public List<Hijo> listarHijosUsuario(long idDatousuario) throws CustomException {

        List<Hijo> hijos = _em.createQuery("SELECT h FROM Hijo as h WHERE h._datoUsuario._id = :id")
                .setParameter("id", idDatousuario)
                .getResultList();

        return hijos;
    }
}
