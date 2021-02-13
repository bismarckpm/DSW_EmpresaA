package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoDato_usuario extends Dao<Dato_usuario>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoDato_usuario( ) throws CustomException
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public List<Object[]> listarDashboardEncuestado(Dato_usuario encuestado, String aux ) throws Exception{

        String hql = "SELECT e._id as idEstudio, e._estatus as estatus, e._nombre as nombre, " +
                "e._fechaInicio as fechaI" +
                " FROM Estudio as e, Solicitud_estudio as se WHERE e._solicitudEstudio._id = se._id and " +
                " se._conCuantasPersonasVive=:PersonasVive and se._disponibilidadEnLinea=:disponibilidadLinea " +
                "and :fechaNacimiento >= se._edadMinimaPoblacion and :fechaNacimiento <= se._edadMaximaPoblacion " +
                "and se._generoPoblacional = :genero and se._nivelEconomico._id = :nivelEconomico and " +
                "se._ocupacion._id = :ocupacion and e._estatus ='En Proceso' and :lugar IN (SELECT re._lugar._id " +
                "FROM Region_estudio as re WHERE re._solicitudEstudio._id = se._id) and se._disponibilidadEnLinea= 'Si'" +
                " and NOT EXISTS (Select r FROM Respuesta as r, Pregunta_estudio as pe, Usuario as u WHERE " +
                "pe._estudio._id = e._id and pe._id = r._preguntaEstudio._id and u._id = r._usuario._id and " +
                "u._datoUsuario._id = :id ) " +
                "ORDER BY e._fechaInicio ";
        Query query = _em.createQuery( hql);
        query.setParameter("PersonasVive", encuestado.get_conCuantasPersonasVive())
                .setParameter("disponibilidadLinea", encuestado.get_disponibilidadEnLinea())
                .setParameter("fechaNacimiento", aux)
                .setParameter("genero", encuestado.get_sexo())
                .setParameter("nivelEconomico", encuestado.get_nivelEconomico().get_id())
                .setParameter("ocupacion", encuestado.get_ocupacion().get_id())
                .setParameter("lugar", encuestado.get_lugar().get_id())
                .setParameter("id", encuestado.get_id());
        List<Object[]> estudiosUsuario = query.getResultList();


        return estudiosUsuario;
    }


    public Dato_usuario getPorUsuario(Long id_usuario){
        try{
            TypedQuery<Dato_usuario> dato_usuario = this._em.createQuery( "SELECT du FROM Dato_usuario du, Usuario us WHERE us._datoUsuario._id = du._id and us._id = :id_usuario", Dato_usuario.class);
            dato_usuario.setParameter("id_usuario", id_usuario);
            dato_usuario.getSingleResult();

            Dato_usuario resultado = dato_usuario.getSingleResult();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }
}
