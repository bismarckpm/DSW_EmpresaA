package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.LugarMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditLugarComando extends BaseComando {
    
    public Lugar lugar;

    public EditLugarComando(Lugar lugar) {
        this.lugar = lugar;
    }

    @Override
    public void execute() {
        try{
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            dao.update(this.lugar);
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Lugar actualizado");
        data.setObjeto(this.lugar.get_id());

        return data;
    }

}
