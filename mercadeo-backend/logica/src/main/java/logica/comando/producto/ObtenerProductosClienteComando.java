package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Usuario;

import java.util.List;

public class ObtenerProductosClienteComando extends BaseComando {

    public List<Producto> productos = null;
    public long id;

    public ObtenerProductosClienteComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        try{
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            DaoUsuario daoU = Fabrica.crear(DaoUsuario.class);
            productos = dao.getProductosCliente(daoU.find(id, Usuario.class));
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando productos del cliente");
        data.setObjeto(this.productos);

        return data;
    }
}
