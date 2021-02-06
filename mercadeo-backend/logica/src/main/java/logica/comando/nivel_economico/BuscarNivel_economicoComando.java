package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_economico;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarNivel_economicoComando extends BaseComando {

    public List<Nivel_economico> nivel_economicos= null;

    @Override
    public void execute() {
        try{
            DaoNivel_economico dao= Fabrica.crear(DaoNivel_economico.class);
            nivel_economicos= dao.findAll(Nivel_economico.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los niveles económicos");
        data.setObjeto(nivel_economicos);

        return data;
    }

}
