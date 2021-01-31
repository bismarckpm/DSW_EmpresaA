package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.JsonObject;
import javax.json.Json;
import java.util.ArrayList;
import java.util.List;

public class AddTelefonoComando extends BaseComando {

    public List<TelefonoDto> telefonoDto;

    public AddTelefonoComando(List<TelefonoDto> telefonoDto) {
        this.telefonoDto = telefonoDto;
    }

    @Override
    public void execute() {

        try {
            DaoTelefono dao = Fabrica.crear(DaoTelefono.class);
            List<Telefono> telefono = TelefonoMapper.mapDtoToEntityInsert(this.telefonoDto);
            List<Telefono> resul = new ArrayList<>();
            for (Telefono telefonox : telefono) {
                resul.add(dao.insert(telefonox));
            }
            this.telefonoDto=TelefonoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        String salida = "";
        for(TelefonoDto tdto : telefonoDto){
            salida= salida + tdto.getId() + " - ";
        }
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Telefono añadido")
                .add("telefono_id",salida).build();

        return data;
    }

}
