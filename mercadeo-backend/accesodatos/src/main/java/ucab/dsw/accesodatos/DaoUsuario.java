package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoUsuario extends Dao<Usuario>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna un usuario completo basado en su correo electrónico
     *
     * @param  correo_entrada  correo electrónico del usuario que se desea obtener
     * @return      un usuario completo cuyo correo coincide con la entrada
     */
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

    /**
     * Este método retorna un usuario tras validar que el código para recuperar contraseña que ingresó
     * es el correcto
     *
     * @param  usuarioAux  usuario del cual se desea validar su código de recuperación de contraseña
     * @return      un usuario cuyo código de recuperación de contraseña ha sido validado
     */
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