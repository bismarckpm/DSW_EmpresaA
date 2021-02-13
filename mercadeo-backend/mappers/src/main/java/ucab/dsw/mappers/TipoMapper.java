package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class TipoMapper {

    public static Tipo mapDtoToEntityInsert(TipoDto tipoDto )throws CustomException
    {
        Tipo tipo = new Tipo();
        if (tipoDto.getNombre() == null || tipoDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del tipo no puede ser nulo ni vacío");
        if(tipoDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre del tipo excede el máximo permitido");
        if (tipoDto.getDescripcion() == null || tipoDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción del tipo no puede ser nulo ni vacío");
        if(tipoDto.getDescripcion().length() > 45)
            throw new CustomException("002", "La descripción del tipo excede el máximo permitido");
        tipo.set_nombre( tipoDto.getNombre() );
        tipo.set_descripcion( tipoDto.getDescripcion() );
        tipo.set_estado( tipoDto.getEstado() );

        return tipo;
    }

    public static Tipo mapDtoToEntityUpdate(long _id,TipoDto tipoDto )throws CustomException
    {
        DaoTipo daoTipo=new DaoTipo();
        if (tipoDto.getNombre() == null || tipoDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del tipo no puede ser nulo ni vacío");
        if(tipoDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre del tipo excede el máximo permitido");
        if (tipoDto.getDescripcion() == null || tipoDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción del tipo no puede ser nulo ni vacío");
        if(tipoDto.getDescripcion().length() > 45)
            throw new CustomException("002", "La descripción del tipo excede el máximo permitido");
        Tipo tipo = daoTipo.find(_id,Tipo.class);

        tipo.set_nombre( tipoDto.getNombre() );
        tipo.set_descripcion( tipoDto.getDescripcion() );
        tipo.set_estado( tipoDto.getEstado() );

        return tipo;
    }

    public static TipoDto mapEntityToDto(  Tipo tipo ) throws CustomException {
        TipoDto tipoDto = new TipoDto();
        if (tipo == null)
            throw new CustomException("004", "El tipo recibido es nulo");
        if (tipo.get_id() == 0 || tipo.get_nombre()=="" || tipo.get_descripcion() == "" ){
            throw new CustomException("001", "Existen atributos inválidos en el tipo");
        }
        DaoCategoria dao = new DaoCategoria();
        tipoDto.setId(tipo.get_id());
        tipoDto.setNombre( tipo.get_nombre() );
        tipoDto.setDescripcion( tipo.get_descripcion() );
        tipoDto.setEstado( tipo.get_estado() );

        return tipoDto;
    }
    
}
