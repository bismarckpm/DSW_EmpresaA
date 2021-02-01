package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditOcupacionComando extends BaseComando {

    public long _id;
    public OcupacionDto ocupacionDto;

    public EditOcupacionComando(long _id, OcupacionDto ocupacionDto) {
        this._id = _id;
        this.ocupacionDto = ocupacionDto;
    }

    @Override
    public void execute() {
        try{
            DaoOcupacion dao = Fabrica.crear(DaoOcupacion.class);
            Ocupacion ocupacion= OcupacionMapper.mapDtoToEntityUpdate(_id,ocupacionDto);
            Ocupacion resul = dao.update(ocupacion);
            this.ocupacionDto=OcupacionMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Ocupacion actualizada")
                .add("ocupacion_nombre",this.ocupacionDto.getNombre()).build();

        return data;
    }

}
