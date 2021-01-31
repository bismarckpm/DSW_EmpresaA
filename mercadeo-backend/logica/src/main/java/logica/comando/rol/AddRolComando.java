package logica.comando.rol;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RolMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRolComando extends BaseComando {

    public RolDto rolDto;

    public AddRolComando(RolDto rolDto) {
        this.rolDto = rolDto;
    }

    @Override
    public void execute() {

        try {
            DaoRol dao = Fabrica.crear(DaoRol.class);
            Rol rol = RolMapper.mapDtoToEntityInsert(this.rolDto);
            Rol resul = dao.insert( rol );
            this.rolDto=RolMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Rol añadido")
                .add("rol_id",this.rolDto.getId()).build();

        return data;
    }

}