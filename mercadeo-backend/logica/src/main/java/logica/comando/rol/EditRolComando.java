package logica.comando.rol;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RolMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRolComando extends BaseComando {
    
    public Rol rol;

    public EditRolComando(Rol rol) {
        this.rol = rol;
    }

    @Override
    public void execute() {
        try{
            DaoRol dao = Fabrica.crear(DaoRol.class);
            dao.update(this.rol);
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Rol actualizado");
        data.setObjeto(this.rol.get_id());

        return data;
    }

}
