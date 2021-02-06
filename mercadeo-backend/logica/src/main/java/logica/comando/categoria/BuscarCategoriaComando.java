package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarCategoriaComando extends BaseComando {

    public List<Categoria> categorias = null;

    @Override
    public void execute() {
        try{
            DaoCategoria dao= Fabrica.crear(DaoCategoria.class);
            categorias= dao.findAll(Categoria.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las categorias");
        data.setObjeto(categorias);

        return data;
    }

}
