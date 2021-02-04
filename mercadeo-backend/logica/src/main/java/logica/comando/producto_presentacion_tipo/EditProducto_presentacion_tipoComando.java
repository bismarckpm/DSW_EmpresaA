package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto_presentacion_tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoPresentacionTipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditProducto_presentacion_tipoComando extends BaseComando {

    public long _id;
    public Producto_presentacion_tipo producto_presentacion_tipo;

    public EditProducto_presentacion_tipoComando(long _id, Producto_presentacion_tipo producto_presentacion_tipo) {
        this._id = _id;
        this.producto_presentacion_tipo = producto_presentacion_tipo;
    }

    @Override
    public void execute() {
        try{
            DaoProducto_presentacion_tipo dao = Fabrica.crear(DaoProducto_presentacion_tipo.class);
            Producto_presentacion_tipo resul = dao.update(this.producto_presentacion_tipo);
            this.producto_presentacion_tipo=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Producto_presentacion_tipo actualizado")
                .add("producto_presentacion_tipo_id",this.producto_presentacion_tipo.get_id()).build();

        return data;
    }

}
