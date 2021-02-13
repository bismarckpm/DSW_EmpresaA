package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class EditTelefonoComando extends BaseComando {
    
    public List<Telefono> telefono;

    public EditTelefonoComando(List<Telefono> telefono) {
        this.telefono = telefono;
    }

    @Override
    public void execute() {
        try{
            DaoTelefono dao = Fabrica.crear(DaoTelefono.class);
            for (Telefono telefonox : telefono) {
                dao.update(telefonox);
            }
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Telefonos actualizados");
        data.setObjeto(this.telefono);

        return data;
    }

}
