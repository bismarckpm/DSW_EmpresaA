package logica.comando.datoUsuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddDatoUsuarioComando extends BaseComando {

    public Dato_usuario datoUsuario;

    public AddDatoUsuarioComando(Dato_usuario datoUsuario) {
        this.datoUsuario = datoUsuario;
    }

    @Override
    public void execute() {

        try {
            DaoDato_usuario dao = Fabrica.crear(DaoDato_usuario.class);
            Dato_usuario resul = dao.insert( this.datoUsuario );
            this.datoUsuario=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Dato_usuario añadido")
                .add("dato_usuario_id",this.datoUsuario.get_id()).build();

        return data;
    }

}
