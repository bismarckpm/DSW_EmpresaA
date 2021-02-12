package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.HijoMapper;

import javax.json.JsonObject;
import javax.json.Json;
import java.util.ArrayList;
import java.util.List;

public class AddHijoComando extends BaseComando {

    public List<Hijo> hijo;

    public AddHijoComando(List<Hijo> hijo) {
        this.hijo = hijo;
    }

    @Override
    public void execute() throws CustomException {

        try {
            DaoHijo dao = Fabrica.crear(DaoHijo.class);
            for (Hijo hijox : hijo) {
                dao.insert(hijox);
            }
        } catch ( CustomException ex ) {
            throw ex;
        }catch (Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Hijos Añadidos");
        data.setObjeto(this.hijo);

        return data;
    }


}
