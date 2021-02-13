package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Response.TipoPregunta.ProductoSolicitudResponse;
import ucab.dsw.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ObtenerProductoSolicitudComando extends BaseComando {

    public List<ProductoSolicitudResponse> ResponseListUpdate = null;
    public long _id;

    public ObtenerProductoSolicitudComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoSolicitud_estudio daoSolicitud_estudio = Fabrica.crear(DaoSolicitud_estudio.class);
            List<Object[]> Lista = daoSolicitud_estudio.ListarProductoSolicitud(_id);

            ResponseListUpdate = new ArrayList<>(Lista.size());

            for (Object[] r : Lista) {
                ResponseListUpdate.add(new ProductoSolicitudResponse((Producto)r[0], (Marca)r[1], (Subcategoria) r[2], (Categoria)r[3]));
            }
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.ResponseListUpdate);
        return data;
    }
}
