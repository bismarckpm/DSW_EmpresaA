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
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import org.apache.commons.codec.digest.DigestUtils;

public class UsuarioMapper {

    public static Usuario mapDtoToEntityInsert(UsuarioDto usuarioDto ) throws CustomException
    {
        Usuario usuario = new Usuario();
        if (usuarioDto.getNombreUsuario() == null || usuarioDto.getNombreUsuario().equals(""))
            throw new CustomException("001", "El nombre del usuario no puede ser nulo ni vacío");
        if(usuarioDto.getNombreUsuario().length() > 45)
            throw new CustomException("002", "El nombre del usuario excede el máximo permitido");
        if (usuarioDto.getCorreo() == null || usuarioDto.getCorreo().equals(""))
            throw new CustomException("001", "El correo del usuario no puede ser nulo ni vacío");
        if(usuarioDto.getCorreo().length() > 45)
            throw new CustomException("002", "El correo del usuario excede el máximo permitido");
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

    public static Usuario mapDtoToEntityUpdate(long _id,UsuarioDto usuarioDto ) throws CustomException
    {
        DaoUsuario daoUsuario=new DaoUsuario();

        Usuario usuario = daoUsuario.find(_id,Usuario.class);

        DaoRol daoRol = new DaoRol();
        DaoDato_usuario  daoDatoUsuario = new DaoDato_usuario();
        if (usuarioDto.getNombreUsuario() == null || usuarioDto.getNombreUsuario().equals(""))
            throw new CustomException("001", "El nombre del usuario no puede ser nulo ni vacío");
        if(usuarioDto.getNombreUsuario().length() > 45)
            throw new CustomException("002", "El nombre del usuario excede el máximo permitido");
        if (usuarioDto.getCorreo() == null || usuarioDto.getCorreo().equals(""))
            throw new CustomException("001", "El correo del usuario no puede ser nulo ni vacío");
        if(usuarioDto.getCorreo().length() > 45)
            throw new CustomException("002", "El correo del usuario excede el máximo permitido");
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

    public static UsuarioDto mapEntityToDto(  Usuario usuario ) throws CustomException {
        UsuarioDto usuarioDto = new UsuarioDto();

        DaoRol daoRol = new DaoRol();
        DaoDato_usuario  daoDatoUsuario = new DaoDato_usuario();
        if (usuario == null)
            throw new CustomException("004", "El usuario recibido es nulo");
        if (usuario.get_id() == 0 || usuario.get_nombreUsuario()=="" || usuario.get_correo() == "" ){
            throw new CustomException("001", "Existen atributos inválidos en el usuario");
        }
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
