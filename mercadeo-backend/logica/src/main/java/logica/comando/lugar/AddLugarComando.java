package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.LugarMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddLugarComando extends BaseComando {

    public Lugar lugar;

    public AddLugarComando(Lugar lugar) {
        this.lugar = lugar;
    }

    @Override
    public void execute() {

        try {
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            Lugar resul = dao.insert( this.lugar );
            this.lugar=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Lugar añadido")
                .add("lugar_id",this.lugar.get_id()).build();

        return data;
    }

}
