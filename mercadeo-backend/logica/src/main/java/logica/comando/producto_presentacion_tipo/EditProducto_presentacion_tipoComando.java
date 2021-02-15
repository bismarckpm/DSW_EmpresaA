package logica.comando.producto_presentacion_tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Producto_presentacion_tipo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.ProductoPresentacionTipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditProducto_presentacion_tipoComando extends BaseComando {
    
    public Producto_presentacion_tipo producto_presentacion_tipo;

    public EditProducto_presentacion_tipoComando(Producto_presentacion_tipo producto_presentacion_tipo) {
        this.producto_presentacion_tipo = producto_presentacion_tipo;
    }

    /**
     * Este comando ejecuta la actualización de una producto_presentacion_tipo
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoProducto_presentacion_tipo dao = Fabrica.crear(DaoProducto_presentacion_tipo.class);
            dao.update(this.producto_presentacion_tipo);
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Producto_presentacion_tipo actualizada");
        data.setObjeto(this.producto_presentacion_tipo);

        return data;
    }

}
