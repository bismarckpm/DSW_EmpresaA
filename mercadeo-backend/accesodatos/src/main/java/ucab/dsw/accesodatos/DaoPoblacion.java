package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoPoblacion extends Dao<Poblacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPoblacion( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }


    public List<Poblacion> listarPoblacionEstudio(long idEstudio){
        List<Poblacion> poblacion = _em.createQuery("SELECT p FROM Poblacion as p " +
                "WHERE p._estudio._id = :id and p._estado = 'A'")
                .setParameter("id", idEstudio)
                .getResultList();
        return poblacion;
    }

    public List<Usuario> listarPoblacionGeneral(long idEstudio){
        List<Usuario> poblacion = _em.createQuery("SELECT u FROM Usuario as u " +
                "WHERE u._id not in (SELECT p._usuario._id FROM Poblacion as p WHERE p._estudio._id=:id) " +
                "and u._estado = 'A'")
                .setParameter("id", idEstudio)
                .getResultList();
        return poblacion;
    }

    public List<Estudio> listarEstudiosUsuario(long idUsuario){
        List<Estudio> estudios = _em.createQuery("SELECT e FROM Estudio as e, Poblacion as p WHERE " +
                "e._id = p._estudio._id and p._usuario = :id and e._estado = 'A'")
                .setParameter("id", idUsuario)
                .getResultList();
        return estudios;
    }

    public List<Usuario> listarPoblacionEstudioUsers(long idEstudio){
        List<Usuario> usuarios = _em.createQuery("SELECT u FROM Usuario as u, Poblacion as p " +
                "WHERE p._estudio._id = :id and u._estado = 'A' and u._id = p._usuario._id")
                .setParameter("id", idEstudio)
                .getResultList();
        return usuarios;
    }

}
