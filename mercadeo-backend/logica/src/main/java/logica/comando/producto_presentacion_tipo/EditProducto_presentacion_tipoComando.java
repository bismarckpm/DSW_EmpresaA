package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto_presentacion_tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Producto_presentacion_tipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditProducto_presentacion_tipoComando extends BaseComando {

    public long _id;
    public Producto_presentacion_tipoDto producto_presentacion_tipoDto;

    public EditProducto_presentacion_tipoComando(long _id, Producto_presentacion_tipoDto producto_presentacion_tipoDto) {
        this._id = _id;
        this.producto_presentacion_tipoDto = producto_presentacion_tipoDto;
    }

    @Override
    public void execute() {
        try{
            DaoProducto_presentacion_tipo dao = Fabrica.crear(DaoProducto_presentacion_tipo.class);
            Producto_presentacion_tipo producto_presentacion_tipo= Producto_presentacion_tipoMapper.mapDtoToEntityUpdate(_id,producto_presentacion_tipoDto);
            Producto_presentacion_tipo resul = dao.update(producto_presentacion_tipo);
            this.producto_presentacion_tipoDto=Producto_presentacion_tipoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto_presentacion_tipo actualizado")
                .add("producto_presentacion_tipo_id",this.producto_presentacion_tipoDto.getId()).build();

        return data;
    }

}
