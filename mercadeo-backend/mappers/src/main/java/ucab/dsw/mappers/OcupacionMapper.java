package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class OcupacionMapper {

    public static Ocupacion mapDtoToEntityInsert(OcupacionDto ocupacionDto ) throws CustomException
    {
        Ocupacion ocupacion = new Ocupacion();
        if (ocupacionDto.getNombre() == null || ocupacionDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la ocupación no puede ser nulo ni vacío");
        if(ocupacionDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre de la ocupación excede el máximo permitido");
        ocupacion.set_nombre( ocupacionDto.getNombre() );
        ocupacion.set_estado( ocupacionDto.getEstado() );

        return ocupacion;
    }

    public static Ocupacion mapDtoToEntityUpdate(long _id,OcupacionDto ocupacionDto ) throws CustomException
    {
        DaoOcupacion daoOcupacion=new DaoOcupacion();
        if (ocupacionDto.getNombre()==null || ocupacionDto.getNombre()=="" )
            throw new CustomException("001", "El nombre de la ocupación no puede ser nulo ni vacío");
        if (ocupacionDto.getNombre().length() > 45 )
            throw new CustomException("002", "El nombre de la ocupación excede el máximo permitido");
        Ocupacion ocupacion = daoOcupacion.find(_id,Ocupacion.class);

        ocupacion.set_nombre( ocupacionDto.getNombre() );
        ocupacion.set_estado( ocupacionDto.getEstado() );

        return ocupacion;
    }

    public static OcupacionDto mapEntityToDto(  Ocupacion ocupacion ) throws CustomException {
        OcupacionDto ocupacionDto = new OcupacionDto();
        if (ocupacion == null)
            throw new CustomException("004", "La ocupación recibida es nula");
        if (ocupacion.get_id() == 0 || ocupacion.get_nombre()==""){
            throw new CustomException("001", "Existen atributos inválidos en la ocupación");
        }
        ocupacionDto.setId(ocupacion.get_id());
        ocupacionDto.setNombre( ocupacion.get_nombre() );
        ocupacionDto.setEstado( "A" );
        return ocupacionDto;
    }
}
