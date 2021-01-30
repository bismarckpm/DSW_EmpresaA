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

public class EditUsuarioComando extends BaseComando {

    public long _id;
    public UsuarioDto usuarioDto;

    public EditUsuarioComando(long _id, UsuarioDto usuarioDto) {
        this._id = _id;
        this.usuarioDto = usuarioDto;
    }

    @Override
    public void execute() {
        try{
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            Usuario usuario= UsuarioMapper.mapDtoToEntityUpdate(_id,usuarioDto);
            Usuario resul = dao.update(usuario);
            this.usuarioDto=UsuarioMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Usuario actualizado")
                .add("usuario_nombre",this.usuarioDto.getNombreUsuario()).build();

        return data;
    }

}
