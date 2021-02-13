package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Producto_presentacion_tipo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarProducto_presentacion_tipoComando extends BaseComando {

    public List<Producto_presentacion_tipo> producto_presentacion_tipos= null;

    @Override
    public void execute() {
        try{
            DaoProducto_presentacion_tipo dao= Fabrica.crear(DaoProducto_presentacion_tipo.class);
            producto_presentacion_tipos= dao.findAll(Producto_presentacion_tipo.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las producto_presentacion_tipos");
        data.setObjeto(producto_presentacion_tipos);

        return data;
    }

}

