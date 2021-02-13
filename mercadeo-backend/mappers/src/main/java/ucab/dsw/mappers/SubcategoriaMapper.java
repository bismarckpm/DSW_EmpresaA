package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class SubcategoriaMapper {

    public static Subcategoria mapDtoToEntityInsert(SubcategoriaDto subcategoriaDto )throws CustomException
    {
        Subcategoria subcategoria = new Subcategoria();
        if (subcategoriaDto.getNombre() == null || subcategoriaDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la subcategoria no puede ser nulo ni vacío");
        if(subcategoriaDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre de la subcategoria excede el máximo permitido");
        if (subcategoriaDto.getDescripcion() == null || subcategoriaDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción de la subcategoria no puede ser nulo ni vacío");
        if(subcategoriaDto.getDescripcion().length() > 255)
            throw new CustomException("002", "La descripción de la subcategoria excede el máximo permitido");
        DaoCategoria daocat = new DaoCategoria();
        subcategoria.set_nombre( subcategoriaDto.getNombre() );
        subcategoria.set_estado( "A" );
        subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
        Categoria categoria = daocat.find (subcategoriaDto.getCategoriaDto().getId(), Categoria.class);
        subcategoria.set_categoria( categoria);

        return subcategoria;
    }

    public static Subcategoria mapDtoToEntityUpdate(long _id,SubcategoriaDto subcategoriaDto )throws CustomException
    {
        DaoSubcategoria daoSubcategoria=new DaoSubcategoria();
        if (subcategoriaDto.getNombre() == null || subcategoriaDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la subcategoria no puede ser nulo ni vacío");
        if(subcategoriaDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre de la subcategoria excede el máximo permitido");
        if (subcategoriaDto.getDescripcion() == null || subcategoriaDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción de la subcategoria no puede ser nulo ni vacío");
        if(subcategoriaDto.getDescripcion().length() > 255)
            throw new CustomException("002", "La descripción de la subcategoria excede el máximo permitido");
        Subcategoria subcategoria = daoSubcategoria.find(_id,Subcategoria.class);
        DaoCategoria daocat = new DaoCategoria();
        subcategoria.set_nombre( subcategoriaDto.getNombre());
        subcategoria.set_estado (subcategoriaDto.getEstado());
        subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
        Categoria categoria = daocat.find (subcategoriaDto.getCategoriaDto().getId(), Categoria.class);
        subcategoria.set_categoria( categoria);

        return subcategoria;
    }

    public static SubcategoriaDto mapEntityToDto(  Subcategoria subcategoria ) throws CustomException {
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
        if (subcategoria == null)
            throw new CustomException("004", "La subcategoria recibida es nula");
        if (subcategoria.get_id() == 0 || subcategoria.get_nombre()=="" || subcategoria.get_descripcion() == "" ){
            throw new CustomException("001", "Existen atributos inválidos en la subcategoria");
        }
        DaoCategoria dao = new DaoCategoria();
        subcategoriaDto.setId(subcategoria.get_id());
        subcategoriaDto.setNombre( subcategoria.get_nombre() );
        subcategoriaDto.setEstado( "A" );
        subcategoriaDto.setDescripcion( subcategoria.get_descripcion() );
        subcategoriaDto.setCategoriaDto( CategoriaMapper.mapEntityToDto(dao.find(subcategoria.get_categoria().get_id(),Categoria.class)));
        return subcategoriaDto;
    }
}
