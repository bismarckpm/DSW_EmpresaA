package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.LugarMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditLugarComando extends BaseComando {

    public long _id;
    public Lugar lugar;

    public EditLugarComando(long _id, Lugar lugar) {
        this._id = _id;
        this.lugar = lugar;
    }

    @Override
    public void execute() {
        try{
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            Lugar resul = dao.update(this.lugar);
            this.lugar=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Lugar actualizado")
                .add("lugar_nombre",this.lugar.get_id()).build();

        return data;
    }

}
