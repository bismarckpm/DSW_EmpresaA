package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class PresentacionMapper {

    public static Presentacion mapDtoToEntityInsert(PresentacionDto presentacionDto ) throws CustomException
    {
        Presentacion presentacion = new Presentacion();

        presentacion.set_titulo( presentacionDto.getTitulo() );
        presentacion.set_caracteristicas( presentacionDto.getCaracteristicas() );
        presentacion.set_estado( "A" );

        return presentacion;
    }

    public static Presentacion mapDtoToEntityUpdate(long _id,PresentacionDto presentacionDto ) throws CustomException
    {
        DaoPresentacion daoPresentacion=new DaoPresentacion();

        Presentacion presentacion = daoPresentacion.find(_id,Presentacion.class);

        presentacion.set_titulo( presentacionDto.getTitulo() );
        presentacion.set_caracteristicas( presentacionDto.getCaracteristicas() );
        presentacion.set_estado( "A" );

        return presentacion;
    }

    public static PresentacionDto mapEntityToDto(  Presentacion presentacion ) throws CustomException {
        PresentacionDto presentacionDto = new PresentacionDto();

        presentacionDto.setId(presentacion.get_id());
        presentacionDto.setTitulo( presentacion.get_titulo() );
        presentacionDto.setCaracteristicas( presentacion.get_caracteristicas() );
        presentacionDto.setEstado( "A" );
        return presentacionDto;
    }

}
