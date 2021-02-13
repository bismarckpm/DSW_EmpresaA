package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class ProductoMapper {
    public static Producto mapDtoToEntityInsert(ProductoDto productoDto )throws CustomException
    {
        Producto producto = new Producto();
        if (productoDto.getNombre() == null || productoDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del producto no puede ser nulo ni vacío");
        if(productoDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre del producto excede el máximo permitido");
        if (productoDto.getDescripcion() == null || productoDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción del producto no puede ser nulo ni vacío");
        if(productoDto.getDescripcion().length() > 300)
            throw new CustomException("002", "La descripción del producto excede el máximo permitido");
        DaoMarca daoMarca = new DaoMarca();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoUsuario daoUsuario = new DaoUsuario();

        producto.set_nombre(productoDto.getNombre());
        producto.set_descripcion( productoDto.getDescripcion() );
        producto.set_estado( productoDto.getEstado() );
        Marca marca = daoMarca.find(productoDto.getMarcaDto().getId(), Marca.class);
        Subcategoria subcategoria = daoSubcategoria.find(productoDto.getSubcategoriaDto().getId(), Subcategoria.class);
        Usuario usuario = daoUsuario.find(productoDto.getUsuarioDto().getId(), Usuario.class);

        producto.set_usuario(usuario);
        producto.set_marca( marca);
        producto.set_subcategoria( subcategoria);

        return producto;
    }

    public static Producto mapDtoToEntityUpdate(long _id,ProductoDto productoDto )throws CustomException
    {
        DaoProducto daoProducto=new DaoProducto();
        if (productoDto.getNombre() == null || productoDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre del producto no puede ser nulo ni vacío");
        if(productoDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre del producto excede el máximo permitido");
        if (productoDto.getDescripcion() == null || productoDto.getDescripcion().equals(""))
            throw new CustomException("001", "La descripción del producto no puede ser nulo ni vacío");
        if(productoDto.getDescripcion().length() > 300)
            throw new CustomException("002", "La descripción del producto excede el máximo permitido");
        Producto producto = daoProducto.find(_id,Producto.class);

        DaoMarca daoMarca = new DaoMarca();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoUsuario daoUsuario = new DaoUsuario();

        producto.set_nombre(productoDto.getNombre());
        producto.set_descripcion( productoDto.getDescripcion() );
        producto.set_estado( productoDto.getEstado() );
        Marca marca = daoMarca.find(productoDto.getMarcaDto().getId(), Marca.class);
        Subcategoria subcategoria = daoSubcategoria.find(productoDto.getSubcategoriaDto().getId(), Subcategoria.class);
        Usuario usuario = daoUsuario.find(productoDto.getUsuarioDto().getId(), Usuario.class);

        producto.set_usuario(usuario);
        producto.set_marca( marca);
        producto.set_subcategoria( subcategoria);

        return producto;
    }

    public static ProductoDto mapEntityToDto(  Producto producto ) throws CustomException {
        ProductoDto productoDto = new ProductoDto();
        if (producto == null)
            throw new CustomException("004", "El producto recibido es nulo");
        if (producto.get_id() == 0 || producto.get_nombre()=="" || producto.get_descripcion() == "" ){
            throw new CustomException("001", "Existen atributos inválidos en el producto");
        }
        DaoMarca daoMarca = new DaoMarca();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoUsuario daoUsuario = new DaoUsuario();

        productoDto.setId(producto.get_id());
        productoDto.setNombre(producto.get_nombre());
        productoDto.setDescripcion( producto.get_descripcion() );
        productoDto.setEstado( producto.get_estado() );

        productoDto.setUsuarioDto(UsuarioMapper.mapEntityToDto(daoUsuario.find(producto.get_usuario().get_id(), Usuario.class)));
        productoDto.setMarcaDto( MarcaMapper.mapEntityToDto(daoMarca.find(producto.get_marca().get_id(), Marca.class)));
        productoDto.setSubcategoriaDto( SubcategoriaMapper.mapEntityToDto(daoSubcategoria.find(producto.get_subcategoria().get_id(), Subcategoria.class)));
        return productoDto;
    }
}
