package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TipoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddTipoComando extends BaseComando {

    public TipoDto tipoDto;

    public AddTipoComando(TipoDto tipoDto) {
        this.tipoDto = tipoDto;
    }

    @Override
    public void execute() {

        try {
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            Tipo tipo = TipoMapper.mapDtoToEntityInsert(this.tipoDto);
            Tipo resul = dao.insert( tipo );
            this.tipoDto=TipoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Tipo añadido")
                .add("tipo_id",this.tipoDto.getId()).build();

        return data;
    }

}
