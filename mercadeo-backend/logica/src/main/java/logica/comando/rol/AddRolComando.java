package logica.comando.rol;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RolMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddRolComando extends BaseComando {

    public Rol rol;

    public AddRolComando(Rol rol) {
        this.rol = rol;
    }

    @Override
    public void execute() {

        try {
            DaoRol dao = Fabrica.crear(DaoRol.class);
            Rol resul = dao.insert( this.rol );
            this.rol=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Rol añadido")
                .add("rol_id",this.rol.get_id()).build();

        return data;
    }

}