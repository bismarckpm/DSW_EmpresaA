package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoUsuario extends Dao<Usuario>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public List<Usuario> conCorreoUsuario(String correo_entrada){
        try{
            TypedQuery<Usuario> usuario = this._em.createNamedQuery( "conCorreoUsuario", Usuario.class);
            usuario.setParameter("correo", correo_entrada);
            usuario.getResultList();

            List<Usuario> resultado = usuario.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    public List<Usuario> validarCodigo(Usuario usuarioAux){
        try{
            TypedQuery<Usuario> usuario = this._em.createNamedQuery( "validarCodigo", Usuario.class);
            usuario.setParameter("correo", usuarioAux.get_correo());
            usuario.setParameter("codigo", usuarioAux.get_codigoRecuperacion());
            usuario.getResultList();

            List<Usuario> resultado = usuario.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }




    public DaoUsuario( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}