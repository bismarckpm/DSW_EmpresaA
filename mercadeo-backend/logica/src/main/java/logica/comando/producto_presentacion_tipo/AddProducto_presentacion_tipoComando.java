package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto_presentacion_tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Producto_presentacion_tipoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddProducto_presentacion_tipoComando extends BaseComando {

    public Producto_presentacion_tipoDto producto_presentacion_tipoDto;

    public AddProducto_presentacion_tipoComando(Producto_presentacion_tipoDto producto_presentacion_tipoDto) {
        this.producto_presentacion_tipoDto = producto_presentacion_tipoDto;
    }

    @Override
    public void execute() {

        try {
            DaoProducto_presentacion_tipo dao = Fabrica.crear(DaoProducto_presentacion_tipo.class);
            Producto_presentacion_tipo producto_presentacion_tipo = Producto_presentacion_tipoMapper.mapDtoToEntityInsert(this.producto_presentacion_tipoDto);
            Producto_presentacion_tipo resul = dao.insert( producto_presentacion_tipo );
            this.producto_presentacion_tipoDto=Producto_presentacion_tipoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto_presentacion_tipo añadida")
                .add("producto_presentacion_tipo_id",this.producto_presentacion_tipoDto.getId()).build();

        return data;
    }

}
