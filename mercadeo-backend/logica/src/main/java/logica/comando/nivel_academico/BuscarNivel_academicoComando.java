package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_academico;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarNivel_academicoComando extends BaseComando {

    public List<Nivel_academico> nivel_academicos= null;

    @Override
    public void execute() {
        try{
            DaoNivel_academico dao= Fabrica.crear(DaoNivel_academico.class);
            nivel_academicos= dao.findAll(Nivel_academico.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los niveles académicos");
        data.setObjeto(this.nivel_academicos);

        return data;
    }

}
