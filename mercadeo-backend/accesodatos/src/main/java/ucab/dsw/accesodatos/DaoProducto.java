package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoProducto extends Dao<Producto>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     * Este método retorna una lista de productos pertenecientes a un cliente
     *
     * @param  usuario  cliente del cual se desean obtener sus productos
     * @return      una lista de productos pertenecientes a un cliente
     */
    public List<Producto> getProductosCliente(Usuario usuario){
        try{
            TypedQuery<Producto> productos = this._em.createNamedQuery( "getProductosCliente", Producto.class);
            productos.setParameter("id_usuario", usuario.get_id()).getResultList();
            productos.getResultList();

            List<Producto> resultado = productos.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    public DaoProducto( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
