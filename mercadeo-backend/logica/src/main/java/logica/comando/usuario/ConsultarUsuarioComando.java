package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.UsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarUsuarioComando extends BaseComando {

    public UsuarioDto usuarioDto;
    public JsonObject usuarioJson;
    public long _id;

    public ConsultarUsuarioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find(_id,Usuario.class);
            this.usuarioDto= UsuarioMapper.mapEntityToDto(usuario);

            usuarioJson= Json.createObjectBuilder()
                    .add("id",usuario.get_id())
                    .add("nombre",usuario.get_nombreUsuario())
                    .add("estado",usuario.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Usuario consultado")
                .add("usuario",usuarioJson).build();

        return data;
    }
}
