package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class EditTelefonoComando extends BaseComando {

    public long _id;
    public List<TelefonoDto> telefonoDto;

    public EditTelefonoComando(List<TelefonoDto> telefonoDto) {
        this.telefonoDto = telefonoDto;
    }

    @Override
    public void execute() {
        try{
            DaoTelefono dao = Fabrica.crear(DaoTelefono.class);
            List<Telefono> telefono= TelefonoMapper.mapDtoToEntityUpdate(telefonoDto);
            List<Telefono> resul = new ArrayList<>();
            for (Telefono telefonox : telefono) {
                resul.add(dao.update(telefonox));
            }
            this.telefonoDto=TelefonoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        String salida = "";
        for(TelefonoDto tdto : telefonoDto){
            salida= salida + tdto.getNumero() + " - ";
        }
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Telefono actualizado")
                .add("telefono_numero",salida).build();

        return data;
    }

}
