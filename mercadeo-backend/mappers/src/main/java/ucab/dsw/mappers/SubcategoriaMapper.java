package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class SubcategoriaMapper {

    public static Subcategoria mapDtoToEntityInsert(SubcategoriaDto subcategoriaDto ) throws CustomException
    {
        Subcategoria subcategoria = new Subcategoria();

        DaoCategoria daocat = new DaoCategoria();
        subcategoria.set_nombre( subcategoriaDto.getNombre() );
        subcategoria.set_estado( "A" );
        subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
        Categoria categoria = daocat.find (subcategoriaDto.getCategoriaDto().getId(), Categoria.class);
        subcategoria.set_categoria( categoria);

        return subcategoria;
    }

    public static Subcategoria mapDtoToEntityUpdate(long _id,SubcategoriaDto subcategoriaDto ) throws CustomException
    {
        DaoSubcategoria daoSubcategoria=new DaoSubcategoria();

        Subcategoria subcategoria = daoSubcategoria.find(_id,Subcategoria.class);

        subcategoria.set_nombre( subcategoriaDto.getNombre());
        subcategoria.set_estado (subcategoriaDto.getEstado());
        subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
        Categoria categoria = new Categoria(subcategoriaDto.getCategoriaDto().getId());
        subcategoria.set_categoria( categoria);

        return subcategoria;
    }

    public static SubcategoriaDto mapEntityToDto(  Subcategoria subcategoria ) throws CustomException {
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();

        DaoCategoria dao = new DaoCategoria();
        subcategoriaDto.setId(subcategoria.get_id());
        subcategoriaDto.setNombre( subcategoria.get_nombre() );
        subcategoriaDto.setEstado( "A" );
        subcategoriaDto.setDescripcion( subcategoria.get_descripcion() );
        subcategoriaDto.setCategoriaDto( CategoriaMapper.mapEntityToDto(dao.find(subcategoria.get_categoria().get_id(),Categoria.class)));
        return subcategoriaDto;
    }
}
