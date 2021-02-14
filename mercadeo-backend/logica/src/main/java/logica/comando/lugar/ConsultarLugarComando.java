package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.LugarMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarLugarComando extends BaseComando {

    public Lugar lugar;
    public long _id;

    public ConsultarLugarComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoLugar dao = new DaoLugar();
            this.lugar = dao.find(_id, Lugar.class);

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
        data.setMensaje("Lugar consultado");
        data.setObjeto(this.lugar);

        return data;
    }
}
