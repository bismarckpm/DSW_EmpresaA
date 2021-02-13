package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarDato_usuarioComando extends BaseComando {

    public List<Dato_usuario> dato_usuarios= null;

    @Override
    public void execute() throws CustomException{
        try{
            DaoDato_usuario dao= Fabrica.crear(DaoDato_usuario.class);
            dato_usuarios= dao.findAll(Dato_usuario.class);
        }catch ( CustomException ex ) {
            throw ex;
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los dato_usuarios");
        data.setObjeto(this.dato_usuarios);

        return data;
    }

}
