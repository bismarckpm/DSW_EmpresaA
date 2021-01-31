package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.UsuarioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddUsuarioComando extends BaseComando {

    public UsuarioDto usuarioDto;

    public AddUsuarioComando(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    @Override
    public void execute() {

        try {
            DaoUsuario dao = Fabrica.crear(DaoUsuario.class);
            Usuario usuario = UsuarioMapper.mapDtoToEntityInsert(this.usuarioDto);
            Usuario resul = dao.insert( usuario );
            this.usuarioDto=UsuarioMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Usuario añadido")
                .add("usuario_id",this.usuarioDto.getId()).build();

        return data;
    }

}
