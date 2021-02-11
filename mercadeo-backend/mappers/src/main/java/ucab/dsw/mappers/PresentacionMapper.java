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
        if (presentacionDto.getTitulo() == null || presentacionDto.getTitulo().equals(""))
            throw new CustomException("001", "El titulo de la presentacion no puede ser nulo ni vacío");
        if(presentacionDto.getTitulo().length() > 45)
            throw new CustomException("002", "El titulo de la presentacion excede el máximo permitido");
        if (presentacionDto.getCaracteristicas() == null || presentacionDto.getCaracteristicas().equals(""))
            throw new CustomException("001", "La caracteristicas de la presentacion no puede ser nulo ni vacío");
        if(presentacionDto.getCaracteristicas().length() > 45)
            throw new CustomException("002", "La caracteristicas de la presentacion excede el máximo permitido");
        presentacion.set_titulo( presentacionDto.getTitulo() );
        presentacion.set_caracteristicas( presentacionDto.getCaracteristicas() );
        presentacion.set_estado( "A" );

        return presentacion;
    }

    public static Presentacion mapDtoToEntityUpdate(long _id,PresentacionDto presentacionDto ) throws CustomException
    {
        DaoPresentacion daoPresentacion=new DaoPresentacion();

        Presentacion presentacion = daoPresentacion.find(_id,Presentacion.class);
        if (presentacion == null)
            throw new CustomException("003","La presentación no existe");
        if(presentacionDto.getTitulo().length() > 45)
            throw new CustomException("002", "El titulo de la presentacion excede el máximo permitido");
        if(presentacionDto.getCaracteristicas().length() > 45)
            throw new CustomException("002", "La caracteristicas de la presentacion excede el máximo permitido");
        presentacion.set_titulo( presentacionDto.getTitulo() );
        presentacion.set_caracteristicas( presentacionDto.getCaracteristicas() );
        presentacion.set_estado( "A" );

        return presentacion;
    }

    public static PresentacionDto mapEntityToDto(  Presentacion presentacion ) throws CustomException {
        PresentacionDto presentacionDto = new PresentacionDto();
        if (presentacion == null)
            throw new CustomException("004", "La presentacion recibida es nula");
        if (presentacion.get_id() == 0 || presentacion.get_titulo()=="" || presentacion.get_caracteristicas() == "" ){
            throw new CustomException("001", "Existen atributos inválidos en la presentación");
        }
        presentacionDto.setId(presentacion.get_id());
        presentacionDto.setTitulo( presentacion.get_titulo() );
        presentacionDto.setCaracteristicas( presentacion.get_caracteristicas() );
        presentacionDto.setEstado( "A" );
        return presentacionDto;
    }

}
