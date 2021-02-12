package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.LugarMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditLugarComando extends BaseComando {

    public long _id;
    public LugarDto lugarDto;

    public EditLugarComando(long _id, LugarDto lugarDto) {
        this._id = _id;
        this.lugarDto = lugarDto;
    }

    @Override
    public void execute() {
        try{
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            Lugar lugar= LugarMapper.mapDtoToEntityUpdate(_id,lugarDto);
            Lugar resul = dao.update(lugar);
            this.lugarDto=LugarMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Lugar actualizado")
                .add("lugar_nombre",this.lugarDto.getNombre()).build();

        return data;
    }

}
