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

public class ObtenerProductoEstudioComando extends BaseComando {

    public Producto producto = null;
    public long id;

    public ObtenerProductoEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        try{
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            Estudio estudio = dao.find(id, Estudio.class);
            producto = estudio.get_solicitudEstudio().get_producto();
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
        data.setObjeto(producto);

        return data;
    }
}
