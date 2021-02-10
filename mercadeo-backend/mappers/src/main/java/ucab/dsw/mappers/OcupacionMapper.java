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

        ocupacion.set_nombre( ocupacionDto.getNombre() );
        ocupacion.set_estado( ocupacionDto.getEstado() );

        return ocupacion;
    }

    public static Ocupacion mapDtoToEntityUpdate(long _id,OcupacionDto ocupacionDto ) throws CustomException
    {
        DaoOcupacion daoOcupacion=new DaoOcupacion();

        Ocupacion ocupacion = daoOcupacion.find(_id,Ocupacion.class);

        ocupacion.set_nombre( ocupacionDto.getNombre() );
        ocupacion.set_estado( ocupacionDto.getEstado() );

        return ocupacion;
    }

    public static OcupacionDto mapEntityToDto(  Ocupacion ocupacion ) throws CustomException {
        OcupacionDto ocupacionDto = new OcupacionDto();

        ocupacionDto.setId(ocupacion.get_id());
        ocupacionDto.setNombre( ocupacion.get_nombre() );
        ocupacionDto.setEstado( "A" );
        return ocupacionDto;
    }
}
