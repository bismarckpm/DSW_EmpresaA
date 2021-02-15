package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.HijoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultarHijoComando extends BaseComando {

    public List<Hijo> hijo;
    public long _id;

    public ConsultarHijoComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de los hijos de un usuario específico
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoHijo dao = new DaoHijo();
            this.hijo = dao.listarHijosUsuario(_id);

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
        data.setMensaje("Hijo consultado");
        data.setObjeto(this.hijo);

        return data;
    }
}
