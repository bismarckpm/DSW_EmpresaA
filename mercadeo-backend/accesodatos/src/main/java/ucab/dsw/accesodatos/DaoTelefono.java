package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Telefono;

import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import java.util.List;

public class DaoTelefono extends Dao<Telefono>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoTelefono( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public List<Telefono> listarTelefonosUsuario(long idDatousuario) {

        List<Telefono> telefonos = _em.createQuery("SELECT t FROM Telefono as t WHERE t._datoUsuario._id = :id")
                .setParameter("id", idDatousuario)
                .getResultList();

        return telefonos;
    }
}
