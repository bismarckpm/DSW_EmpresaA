package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.LugarMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddLugarComando extends BaseComando {

    public LugarDto lugarDto;

    public AddLugarComando(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    @Override
    public void execute() {

        try {
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            Lugar lugar = LugarMapper.mapDtoToEntityInsert(this.lugarDto);
            Lugar resul = dao.insert( lugar );
            this.lugarDto=LugarMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Lugar añadido")
                .add("lugar_id",this.lugarDto.getId()).build();

        return data;
    }

}
