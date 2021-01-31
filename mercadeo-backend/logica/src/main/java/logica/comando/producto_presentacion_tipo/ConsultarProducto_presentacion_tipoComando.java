package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto_presentacion_tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoPresentacionTipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarProducto_presentacion_tipoComando extends BaseComando {

    public Producto_presentacion_tipoDto producto_presentacion_tipoDto;
    public JsonObject producto_presentacion_tipoJson;
    public long _id;

    public ConsultarProducto_presentacion_tipoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoProducto_presentacion_tipo dao = new DaoProducto_presentacion_tipo();
            Producto_presentacion_tipo producto_presentacion_tipo = dao.find(_id,Producto_presentacion_tipo.class);
            this.producto_presentacion_tipoDto= ProductoPresentacionTipoMapper.mapEntityToDto(producto_presentacion_tipo);

            producto_presentacion_tipoJson= Json.createObjectBuilder()
                    .add("id",producto_presentacion_tipo.get_id())
                    .add("nombre",producto_presentacion_tipo.get_producto() + " " + producto_presentacion_tipo.get_presentacion().get_titulo() + " " + producto_presentacion_tipo.get_tipo().get_nombre())
                    .add("estado",producto_presentacion_tipo.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto_presentacion_tipo consultado")
                .add("producto_presentacion_tipo",producto_presentacion_tipoJson).build();

        return data;
    }
}
