package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.CategoriaMapper;

import javax.json.JsonObject;
import javax.json.Json;
import javax.ws.rs.core.Response;

public class AddCategoriaComando extends BaseComando {

    public Categoria categoria;

    public AddCategoriaComando(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void execute() {

        try {
            DaoCategoria dao = Fabrica.crear(DaoCategoria.class);
            dao.insert( this.categoria );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Categoria Añadida");
        data.setObjeto(this.categoria);

        return data;
    }

}
