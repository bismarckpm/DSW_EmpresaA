package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class RolMapper {

    public static Rol mapDtoToEntityInsert(RolDto rolDto ) throws CustomException
    {
        Rol rol = new Rol();
        if (rolDto.getNombre() == null || rolDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del rol no puede ser nulo ni vacío");
        if(rolDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre del rol excede el máximo permitido");
        if (rolDto.getDescripcion() == null || rolDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción del rol no puede ser nulo ni vacío");
        if(rolDto.getDescripcion().length() > 45)
            throw new CustomException("002", "La descripción del rol excede el máximo permitido");
        rol.set_nombre( rolDto.getNombre() );
        rol.set_estado( rolDto.getEstado() );
        rol.set_descripcion( rolDto.getDescripcion() );

        return rol;
    }

    public static Rol mapDtoToEntityUpdate(long _id,RolDto rolDto ) throws CustomException
    {
        DaoRol daoRol=new DaoRol();

        Rol rol = daoRol.find(_id,Rol.class);
        if (rolDto.getNombre() == null || rolDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del rol no puede ser nulo ni vacío");
        if(rolDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre del rol excede el máximo permitido");
        if (rolDto.getDescripcion() == null || rolDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción del rol no puede ser nulo ni vacío");
        if(rolDto.getDescripcion().length() > 45)
            throw new CustomException("002", "La descripción del rol excede el máximo permitido");
        rol.set_nombre( rolDto.getNombre() );
        rol.set_estado( rolDto.getEstado() );
        rol.set_descripcion( rolDto.getDescripcion() );

        return rol;
    }

    public static RolDto mapEntityToDto(  Rol rol ) throws CustomException {
        RolDto rolDto = new RolDto();
        if (rol == null)
            throw new CustomException("004", "El rol recibido es nulo");
        if (rol.get_id() == 0 || rol.get_nombre()=="" || rol.get_descripcion() == "" ){
            throw new CustomException("001", "Existen atributos inválidos en el rol");
        }
        rolDto.setId(rol.get_id());
        rolDto.setNombre( rol.get_nombre() );
        rolDto.setEstado( rol.get_estado() );
        rolDto.setDescripcion( rol.get_descripcion() );

        return rolDto;
    }
    
}
