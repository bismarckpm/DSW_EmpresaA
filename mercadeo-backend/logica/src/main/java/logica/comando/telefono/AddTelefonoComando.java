package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.JsonObject;
import javax.json.Json;
import java.util.ArrayList;
import java.util.List;

public class AddTelefonoComando extends BaseComando {

    public List<Telefono> telefono;

    public AddTelefonoComando(List<Telefono> telefono) {
        this.telefono = telefono;
    }

    @Override
    public void execute() {

        try {
            DaoTelefono dao = Fabrica.crear(DaoTelefono.class);
            for (Telefono telefonox : telefono) {
                dao.insert(telefonox);
            }
        } catch (Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Telefonos Añadidos");
        data.setObjeto(this.telefono);

        return data;
    }

}
