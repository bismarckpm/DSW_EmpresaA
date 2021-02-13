package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Producto_presentacion_tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.ProductoPresentacionTipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarProducto_presentacion_tipoComando extends BaseComando {

    public Producto_presentacion_tipo producto_presentacion_tipo;
    public long _id;

    public ConsultarProducto_presentacion_tipoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoProducto_presentacion_tipo dao = new DaoProducto_presentacion_tipo();
            this.producto_presentacion_tipo = dao.find(_id, Producto_presentacion_tipo.class);

        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Producto_presentacion_tipo consultada");
        data.setObjeto(this.producto_presentacion_tipo);

        return data;
    }
}
