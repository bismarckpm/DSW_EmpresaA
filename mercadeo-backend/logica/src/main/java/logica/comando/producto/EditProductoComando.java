package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditProductoComando extends BaseComando {

    public long _id;
    public ProductoDto productoDto;

    public EditProductoComando(long _id, ProductoDto productoDto) {
        this._id = _id;
        this.productoDto = productoDto;
    }

    @Override
    public void execute() {
        try{
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            Producto producto= ProductoMapper.mapDtoToEntityUpdate(_id,productoDto);
            Producto resul = dao.update(producto);
            this.productoDto=ProductoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto actualizado")
                .add("producto_nombre",this.productoDto.getNombre()).build();

        return data;
    }

}
