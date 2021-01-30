package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddTelefonoComando extends BaseComando {

    public TelefonoDto telefonoDto;

    public AddTelefonoComando(TelefonoDto telefonoDto) {
        this.telefonoDto = telefonoDto;
    }

    @Override
    public void execute() {

        try {
            DaoTelefono dao = Fabrica.crear(DaoTelefono.class);
            Telefono telefono = TelefonoMapper.mapDtoToEntityInsert(this.telefonoDto);
            Telefono resul = dao.insert( telefono );
            this.telefonoDto=TelefonoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Telefono añadido")
                .add("telefono_id",this.telefonoDto.getId()).build();

        return data;
    }

}
