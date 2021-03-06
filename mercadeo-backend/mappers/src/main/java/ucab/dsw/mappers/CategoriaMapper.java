package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.CustomException;

public class CategoriaMapper {

    public static Categoria mapDtoToEntityInsert(CategoriaDto dto ) throws CustomException
    {
        Categoria entity= new Categoria();
        if (dto.getNombre() == null || dto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la categoría no puede ser nulo ni vacío");
        if(dto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre de la categoría excede el máximo permitido");
        entity.set_nombre(dto.getNombre());
        entity.set_estado("A");

        return entity;
    }

    public static Categoria mapDtoToEntityUpdate(long _id,CategoriaDto dto ) throws CustomException
    {
        DaoCategoria daoCategoria=new DaoCategoria();
        if (dto.getNombre()==null || dto.getNombre()=="" )
            throw new CustomException("001", "El nombre de la categoría no puede ser nulo ni vacío");
        if (dto.getNombre().length() > 45 )
            throw new CustomException("002", "El nombre de la categoría excede el máximo permitido");

        Categoria entity = daoCategoria.find(_id,Categoria.class);

        entity.set_nombre(dto.getNombre());
        entity.set_estado(dto.getEstado());

        return entity;
    }

    public static CategoriaDto mapEntityToDto(  Categoria entity ) throws CustomException {
        CategoriaDto dto = new CategoriaDto();
        if (entity == null)
            throw new CustomException("004", "La categoría recibida es nula");
        if (entity.get_id() == 0 || entity.get_nombre()==""){
            throw new CustomException("001", "Existen atributos inválidos en la categoría");
        }
        dto.setId(entity.get_id());
        dto.setNombre( entity.get_nombre());
        dto.setEstado(entity.get_estado());

        return dto;
    }

}
