package logica.comando.producto;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddProductoComando extends BaseComando {

    public ProductoDto productoDto;

    public AddProductoComando(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    @Override
    public void execute() {

        try {
            DaoProducto dao = Fabrica.crear(DaoProducto.class);
            Producto producto = ProductoMapper.mapDtoToEntityInsert(this.productoDto);
            Producto resul = dao.insert( producto );
            this.productoDto=ProductoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto añadido")
                .add("producto_id",this.productoDto.getId()).build();

        return data;
    }

}
