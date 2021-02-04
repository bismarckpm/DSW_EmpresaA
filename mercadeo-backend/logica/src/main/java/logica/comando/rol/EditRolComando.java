package logica.comando.rol;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RolMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRolComando extends BaseComando {

    public long _id;
    public Rol rol;

    public EditRolComando(long _id, Rol rol) {
        this._id = _id;
        this.rol = rol;
    }

    @Override
    public void execute() {
        try{
            DaoRol dao = Fabrica.crear(DaoRol.class);
            Rol resul = dao.update(rol);
            this.rol=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Rol actualizado")
                .add("rol_nombre",this.rol.get_id()).build();

        return data;
    }

}
