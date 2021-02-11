package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class ProductoPresentacionTipoMapper {
    public static Producto_presentacion_tipo mapDtoToEntityInsert(Producto_presentacion_tipoDto producto_presentacion_tipoDto ) throws CustomException
    {
        Producto_presentacion_tipo producto_presentacion_tipo = new Producto_presentacion_tipo();

        DaoProducto daoProducto = new DaoProducto();
        DaoTipo daoTipo = new DaoTipo();
        DaoPresentacion daoPresentacion = new DaoPresentacion();

        Producto producto = daoProducto.find(producto_presentacion_tipoDto.getProductoDto().getId(), Producto.class);
        Tipo tipo = daoTipo.find(producto_presentacion_tipoDto.getTipoDto().getId(), Tipo.class);
        Presentacion presentacion = daoPresentacion.find(producto_presentacion_tipoDto.getPresentacionDto().getId(), Presentacion.class);
        if (producto == null)
            throw new CustomException("003","El producto no existe");
        if (tipo == null)
            throw new CustomException("003","El tipo no existe");
        if (presentacion == null)
            throw new CustomException("003","La presentacion no existe");
        producto_presentacion_tipo.set_estado( "A" );
        producto_presentacion_tipo.set_producto( producto);
        producto_presentacion_tipo.set_tipo(tipo);
        producto_presentacion_tipo.set_presentacion(presentacion);

        return producto_presentacion_tipo;
    }

    public static Producto_presentacion_tipo mapDtoToEntityUpdate(long _id,Producto_presentacion_tipoDto producto_presentacion_tipoDto ) throws CustomException
    {
        DaoProducto_presentacion_tipo daoProducto_presentacion_tipo=new DaoProducto_presentacion_tipo();

        Producto_presentacion_tipo producto_presentacion_tipo = daoProducto_presentacion_tipo.find(_id,Producto_presentacion_tipo.class);

        DaoProducto daoProducto = new DaoProducto();
        DaoTipo daoTipo = new DaoTipo();
        DaoPresentacion daoPresentacion = new DaoPresentacion();

        Producto producto = daoProducto.find(producto_presentacion_tipoDto.getProductoDto().getId(), Producto.class);
        Tipo tipo = daoTipo.find(producto_presentacion_tipoDto.getTipoDto().getId(), Tipo.class);
        Presentacion presentacion = daoPresentacion.find(producto_presentacion_tipoDto.getPresentacionDto().getId(), Presentacion.class);
        if (producto == null)
            throw new CustomException("003","El producto no existe");
        if (tipo == null)
            throw new CustomException("003","El tipo no existe");
        if (presentacion == null)
            throw new CustomException("003","La presentacion no existe");
        producto_presentacion_tipo.set_estado( "A" );
        producto_presentacion_tipo.set_producto( producto);
        producto_presentacion_tipo.set_tipo(tipo);
        producto_presentacion_tipo.set_presentacion(presentacion);

        return producto_presentacion_tipo;
    }

    public static Producto_presentacion_tipoDto mapEntityToDto(  Producto_presentacion_tipo producto_presentacion_tipo ) throws CustomException {
        Producto_presentacion_tipoDto producto_presentacion_tipoDto = new Producto_presentacion_tipoDto();

        DaoProducto daoProducto = new DaoProducto();
        DaoTipo daoTipo = new DaoTipo();
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        if (producto_presentacion_tipo == null)
            throw new CustomException("003","El producto_presentacion_tipo no existe");
        Producto producto = daoProducto.find(producto_presentacion_tipoDto.getProductoDto().getId(), Producto.class);
        Tipo tipo = daoTipo.find(producto_presentacion_tipoDto.getTipoDto().getId(), Tipo.class);
        Presentacion presentacion = daoPresentacion.find(producto_presentacion_tipoDto.getPresentacionDto().getId(), Presentacion.class);
        if (producto == null)
            throw new CustomException("003","El producto no existe");
        if (tipo == null)
            throw new CustomException("003","El tipo no existe");
        if (presentacion == null)
            throw new CustomException("003","La presentacion no existe");
        producto_presentacion_tipoDto.setId(producto_presentacion_tipo.get_id());
        producto_presentacion_tipoDto.setEstado( "A" );
        producto_presentacion_tipoDto.setProductoDto( ProductoMapper.mapEntityToDto(daoProducto.find(producto_presentacion_tipo.get_producto().get_id(), Producto.class)));
        producto_presentacion_tipoDto.setTipoDto(TipoMapper.mapEntityToDto(daoTipo.find(producto_presentacion_tipo.get_tipo().get_id(), Tipo.class)));
        producto_presentacion_tipoDto.setPresentacionDto(PresentacionMapper.mapEntityToDto(daoPresentacion.find(producto_presentacion_tipo.get_presentacion().get_id(), Presentacion.class)));

        return producto_presentacion_tipoDto;
    }
}
