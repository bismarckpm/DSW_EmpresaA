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

public class ConsultarRolComando extends BaseComando {

    public RolDto rolDto;
    public JsonObject rolJson;
    public long _id;

    public ConsultarRolComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoRol dao = new DaoRol();
            Rol rol = dao.find(_id,Rol.class);
            this.rolDto= RolMapper.mapEntityToDto(rol);

            rolJson= Json.createObjectBuilder()
                    .add("_id",rol.get_id())
                    .add("_nombre",rol.get_nombre())
                    .add("_descripcion",rol.get_descripcion())
                    .add("_estado",rol.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Rol consultado")
                .add("rol",rolJson).build();

        return data;
    }
}
