package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarTelefonoComando extends BaseComando {

    public List<Telefono> telefonos= null;

    @Override
    public void execute() {
        try{
            DaoTelefono dao= Fabrica.crear(DaoTelefono.class);
            telefonos= dao.findAll(Telefono.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los telefonos");
        data.setObjeto(this.telefonos);

        return data;
    }

}
