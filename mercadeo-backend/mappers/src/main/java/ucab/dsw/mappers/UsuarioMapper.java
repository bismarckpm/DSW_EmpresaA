package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import org.apache.commons.codec.digest.DigestUtils;

public class UsuarioMapper {

    public static Usuario mapDtoToEntityInsert(UsuarioDto usuarioDto )
    {
        Usuario usuario = new Usuario();

        DaoRol daoRol = new DaoRol();
        DaoDato_usuario  daoDatoUsuario = new DaoDato_usuario();

        Rol rol = daoRol.find(usuarioDto.getRolDto().getId(), Rol.class);
        Dato_usuario datoUsuario = daoDatoUsuario.find(usuarioDto.getDatoUsuarioDto().getId(), Dato_usuario.class);

        usuario.set_correo(usuarioDto.getCorreo());
        usuario.set_password(DigestUtils.md5Hex(usuarioDto.getPassword()));
        usuario.set_estado("A");
        usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
        usuario.set_datoUsuario(datoUsuario);
        usuario.set_rol(rol);

        return usuario;
    }

    public static Usuario mapDtoToEntityUpdate(long _id,UsuarioDto usuarioDto )
    {
        DaoUsuario daoUsuario=new DaoUsuario();

        Usuario usuario = daoUsuario.find(_id,Usuario.class);

        DaoRol daoRol = new DaoRol();
        DaoDato_usuario  daoDatoUsuario = new DaoDato_usuario();

        Rol rol = daoRol.find(usuarioDto.getRolDto().getId(), Rol.class);
        Dato_usuario datoUsuario = daoDatoUsuario.find(usuarioDto.getDatoUsuarioDto().getId(), Dato_usuario.class);


        usuario.set_correo(usuarioDto.getCorreo());
        usuario.set_password(DigestUtils.md5Hex(usuarioDto.getPassword()));
        usuario.set_estado("A");
        usuario.set_nombreUsuario(usuarioDto.getNombreUsuario());
        usuario.set_datoUsuario(datoUsuario);
        usuario.set_rol(rol);

        return usuario;
    }

    public static UsuarioDto mapEntityToDto(  Usuario usuario ) throws PruebaExcepcion {
        UsuarioDto usuarioDto = new UsuarioDto();

        DaoRol daoRol = new DaoRol();
        DaoDato_usuario  daoDatoUsuario = new DaoDato_usuario();

        usuarioDto.setId(usuario.get_id());
        usuarioDto.setCorreo(usuario.get_correo());
        usuarioDto.setPassword(DigestUtils.md5Hex(usuario.get_password()));
        usuarioDto.setEstado("A");
        usuarioDto.setNombreUsuario(usuario.get_nombreUsuario());
        usuarioDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(usuario.get_datoUsuario().get_id(),Dato_usuario.class)));
        usuarioDto.setRolDto(RolMapper.mapEntityToDto(daoRol.find(usuario.get_rol().get_id(),Rol.class)));

        return usuarioDto;
    }
    
}
