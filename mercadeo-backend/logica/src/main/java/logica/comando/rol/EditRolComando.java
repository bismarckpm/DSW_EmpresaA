package logica.comando.rol;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RolMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRolComando extends BaseComando {

    public long _id;
    public RolDto rolDto;

    public EditRolComando(long _id, RolDto rolDto) {
        this._id = _id;
        this.rolDto = rolDto;
    }

    @Override
    public void execute() {
        try{
            DaoRol dao = Fabrica.crear(DaoRol.class);
            Rol rol= RolMapper.mapDtoToEntityUpdate(_id,rolDto);
            Rol resul = dao.update(rol);
            this.rolDto=RolMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Rol actualizado")
                .add("rol_nombre",this.rolDto.getNombre()).build();

        return data;
    }

}
