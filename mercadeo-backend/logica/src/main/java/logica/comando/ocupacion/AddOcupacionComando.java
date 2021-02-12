package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddOcupacionComando extends BaseComando {

    public OcupacionDto ocupacionDto;

    public AddOcupacionComando(OcupacionDto ocupacionDto) {
        this.ocupacionDto = ocupacionDto;
    }

    @Override
    public void execute() {

        try {
            DaoOcupacion dao = Fabrica.crear(DaoOcupacion.class);
            Ocupacion ocupacion = OcupacionMapper.mapDtoToEntityInsert(this.ocupacionDto);
            Ocupacion resul = dao.insert( ocupacion );
            this.ocupacionDto=OcupacionMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Ocupacion añadida")
                .add("ocupacion_id",this.ocupacionDto.getId()).build();

        return data;
    }

}
