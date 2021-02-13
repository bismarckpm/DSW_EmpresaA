package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ObtenerEstudiosClienteComando extends BaseComando {

    public List<Estudio> estudios = null;
    public long id;

    public ObtenerEstudiosClienteComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            estudios = dao.getEstudiosCliente(id);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando estudios del usuario");
        data.setObjeto(this.estudios);

        return data;
    }
}
