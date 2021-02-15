package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarUsuarioComando extends BaseComando {

    public List<Usuario> usuarios= null;

    /**
     * Este comando ejecuta la consulta de los usuarios registrados
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoUsuario dao= Fabrica.crear(DaoUsuario.class);
            usuarios= dao.findAll(Usuario.class);
        }catch ( CustomException ex ) {
            throw ex;
        }
        catch ( Exception ex ){
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los usuarios");
        data.setObjeto(this.usuarios);

        return data;
    }

}
