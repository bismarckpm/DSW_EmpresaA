package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditTelefonoComando extends BaseComando {

    public long _id;
    public TelefonoDto telefonoDto;

    public EditTelefonoComando(long _id, TelefonoDto telefonoDto) {
        this._id = _id;
        this.telefonoDto = telefonoDto;
    }

    @Override
    public void execute() {
        try{
            DaoTelefono dao = Fabrica.crear(DaoTelefono.class);
            Telefono telefono= TelefonoMapper.mapDtoToEntityUpdate(_id,telefonoDto);
            Telefono resul = dao.update(telefono);
            this.telefonoDto=TelefonoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Telefono actualizado")
                .add("telefono_numero",this.telefonoDto.getNumero()).build();

        return data;
    }

}
