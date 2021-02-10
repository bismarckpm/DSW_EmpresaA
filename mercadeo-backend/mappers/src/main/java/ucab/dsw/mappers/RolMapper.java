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

        rol.set_nombre( rolDto.getNombre() );
        rol.set_estado( rolDto.getEstado() );
        rol.set_descripcion( rolDto.getDescripcion() );

        return rol;
    }

    public static Rol mapDtoToEntityUpdate(long _id,RolDto rolDto ) throws CustomException
    {
        DaoRol daoRol=new DaoRol();

        Rol rol = daoRol.find(_id,Rol.class);

        rol.set_nombre( rolDto.getNombre() );
        rol.set_estado( rolDto.getEstado() );
        rol.set_descripcion( rolDto.getDescripcion() );

        return rol;
    }

    public static RolDto mapEntityToDto(  Rol rol ) throws CustomException {
        RolDto rolDto = new RolDto();

        rolDto.setId(rol.get_id());
        rolDto.setNombre( rol.get_nombre() );
        rolDto.setEstado( rol.get_estado() );
        rolDto.setDescripcion( rol.get_descripcion() );

        return rolDto;
    }
    
}
