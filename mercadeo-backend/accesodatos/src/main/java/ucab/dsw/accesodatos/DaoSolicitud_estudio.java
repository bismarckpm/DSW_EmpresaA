package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.*;
import javax.ws.rs.PathParam;
import java.time.LocalDate;
import java.util.List;

public class DaoSolicitud_estudio extends Dao<Solicitud_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSolicitud_estudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    /**
     * Este método retorna una lista de solicitudes de estudio pertenecientes a un cliente específico
     *
     * @param  id  id del cliente del cual se desea obtener sus solicitudes de estudio
     * @return      una lista de solicitudes de estudio pertenecientes a un cliente específico
     */
    public List<Solicitud_estudio> solicitudesCliente(Long id){
        try{
            TypedQuery<Solicitud_estudio> solicitudes = this._em.createQuery( "SELECT se FROM Solicitud_estudio se WHERE se._usuario._id = :id_usuario ", Solicitud_estudio.class);
            solicitudes.setParameter("id_usuario", id);
            solicitudes.getResultList();

            List<Solicitud_estudio> resultado = solicitudes.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public List<Object[]> ListarProductoSolicitud(long idSolicitud){

        String hql = "select p, m, s, c" +
                " from Producto as p, Marca as m, Subcategoria  as s, Categoria as c, Solicitud_estudio as se " +
                "where p._subcategoria._id = s._id and s._categoria._id = c._id and p._marca._id = m._id and " +
                "se._producto._id = p._id and se._id = :id";
        Query query = this._em.createQuery( hql);
        query.setParameter("id", idSolicitud);
        List<Object[]> Lista = query.getResultList();

        return Lista;
    }

    public List<Object[]> listarEstudiosRecomendados(long idSolicitud){

        Solicitud_estudio solicitud_estudio = find (idSolicitud, Solicitud_estudio.class);

        String hql = "SELECT e._id as idEstudio, e._estatus as estatus, e._nombre as nombre, " +
                "e._fechaInicio as fechaI" +
                " FROM Estudio as e, Solicitud_estudio as se WHERE e._solicitudEstudio._id = se._id and " +
                " se._conCuantasPersonasVive=:PersonasVive and se._disponibilidadEnLinea=:disponibilidadLinea " +
                "and :edadMinima >= se._edadMinimaPoblacion and :edadMaxima <= se._edadMaximaPoblacion " +
                "and se._generoPoblacional = :genero and se._nivelEconomico._id = :nivelEconomico and " +
                "se._ocupacion._id = :ocupacion and se._id <> :idSolicitud " +
                "ORDER BY e._fechaInicio ";
        Query query = _em.createQuery( hql);
        query.setParameter("PersonasVive", solicitud_estudio.get_conCuantasPersonasVive())
                .setParameter("disponibilidadLinea", solicitud_estudio.get_disponibilidadEnLinea())
                .setParameter("edadMinima", solicitud_estudio.get_edadMinimaPoblacion())
                .setParameter("edadMaxima", solicitud_estudio.get_edadMaximaPoblacion())
                .setParameter("genero", solicitud_estudio.get_generoPoblacional())
                .setParameter("nivelEconomico", solicitud_estudio.get_nivelEconomico().get_id())
                .setParameter("ocupacion", solicitud_estudio.get_ocupacion().get_id())
                .setParameter("idSolicitud", idSolicitud);
        List<Object[]> estudios = query.getResultList();

        return estudios;
    }

    public List<Usuario> listarPoblacionEstudio(long idSolicitud){

        Solicitud_estudio solicitud_estudio = find (idSolicitud, Solicitud_estudio.class);

        LocalDate ahora = LocalDate.now();

        List<Usuario> poblacion = _em.createQuery("SELECT u" +
                " FROM Dato_usuario as da, Usuario as u WHERE u._datoUsuario._id = da._id and " +
                "da._conCuantasPersonasVive=:PersonasVive and da._disponibilidadEnLinea=:disponibilidadLinea " +
                "and :ahora - FUNCTION('YEAR',da._fechaNacimiento) >= :edadMinima and :ahora - FUNCTION('YEAR', da._fechaNacimiento) <= :edadMaxima  " +
                "and da._sexo = :genero and da._nivelEconomico._id = :nivelEconomico and da._ocupacion._id = :ocupacion " +
                "and da._lugar IN (SELECT re._lugar._id  FROM Region_estudio as re WHERE re._solicitudEstudio._id = :id) " +
                "ORDER BY u._id ")
                .setParameter("PersonasVive", solicitud_estudio.get_conCuantasPersonasVive())
                .setParameter("disponibilidadLinea", solicitud_estudio.get_disponibilidadEnLinea())
                .setParameter("ahora", ahora.getYear())
                .setParameter("edadMinima", Integer.parseInt(solicitud_estudio.get_edadMinimaPoblacion()))
                .setParameter("edadMaxima", Integer.parseInt(solicitud_estudio.get_edadMaximaPoblacion()))
                .setParameter("genero", solicitud_estudio.get_generoPoblacional())
                .setParameter("nivelEconomico", solicitud_estudio.get_nivelEconomico().get_id())
                .setParameter("ocupacion", solicitud_estudio.get_ocupacion().get_id())
                .setParameter("id", solicitud_estudio.get_id())
                .getResultList();

        return poblacion;
    }

    public List<Solicitud_estudio> listarSolicitudes(){
        List<Solicitud_estudio> solicitudes = _em.createQuery("SELECT s FROM Solicitud_estudio as s " +
                "WHERE s._estado = 'A'")
                .getResultList();
        return solicitudes;
    }

}
