package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.HijoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class EditHijoComando extends BaseComando {

    public List<Hijo> hijo;

    public EditHijoComando(List<Hijo> hijo) {
        this.hijo = hijo;
    }


    @Override
    public void execute() throws CustomException{
        try{
            DaoHijo dao = Fabrica.crear(DaoHijo.class);
            for (Hijo hijox : hijo) {
                dao.update(hijox);
            }
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Hijos actualizados");
        data.setObjeto(this.hijo);

        return data;
    }

}
