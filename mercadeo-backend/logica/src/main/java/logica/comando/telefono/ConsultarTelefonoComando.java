package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class ConsultarTelefonoComando extends BaseComando {

    public List<Telefono> telefono;
    public long _id;

    public ConsultarTelefonoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoTelefono dao = new DaoTelefono();
            this.telefono = dao.listarTelefonosUsuario(_id);

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Telefono consultado");
        data.setObjeto(this.telefono);

        return data;
    }
}
