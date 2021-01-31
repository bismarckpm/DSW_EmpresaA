package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditTipoComando extends BaseComando {

    public long _id;
    public TipoDto tipoDto;

    public EditTipoComando(long _id, TipoDto tipoDto) {
        this._id = _id;
        this.tipoDto = tipoDto;
    }

    @Override
    public void execute() {
        try{
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            Tipo tipo= TipoMapper.mapDtoToEntityUpdate(_id,tipoDto);
            Tipo resul = dao.update(tipo);
            this.tipoDto=TipoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Tipo actualizado")
                .add("tipo_nombre",this.tipoDto.getNombre()).build();

        return data;
    }

}
