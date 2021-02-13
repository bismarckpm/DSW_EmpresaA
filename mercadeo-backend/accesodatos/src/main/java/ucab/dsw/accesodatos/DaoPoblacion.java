package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoPoblacion extends Dao<Poblacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPoblacion( ) throws CustomException
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
                "WHERE u._id not in (SELECT p._usuario._id FROM Poblacion as p WHERE p._estudio._id=:id and p._estado='A') " +
                "and u._estado = 'A' and u._rol._id = 4")
                .setParameter("id", idEstudio)
                .getResultList();
        return poblacion;
    }

    public List<Estudio> listarEstudiosUsuario(long idUsuario){
        List<Estudio> estudios = _em.createQuery("SELECT e FROM Estudio as e, Poblacion as p, Usuario as u, " +
                "Solicitud_estudio as s, Dato_usuario as d WHERE u._id = p._usuario._id and p._estudio._id = e._id and " +
                "e._solicitudEstudio._id = s._id and d._id = u._datoUsuario._id and u._id = :id and e._estado = 'A'" +
                " and s._disponibilidadEnLinea ='Si' and d._disponibilidadEnLinea = 'Si'")
                .setParameter("id", idUsuario)
                .getResultList();
        return estudios;
    }

    public List<Usuario> listarPoblacionEstudioUsers(long idEstudio){
        List<Usuario> usuarios = _em.createQuery("SELECT u FROM Usuario as u, Poblacion as p " +
                "WHERE u._id = p._usuario._id and p._estudio._id = :id and u._estado = 'A' and p._estado='A'")
                .setParameter("id", idEstudio)
                .getResultList();
        return usuarios;
    }

}
