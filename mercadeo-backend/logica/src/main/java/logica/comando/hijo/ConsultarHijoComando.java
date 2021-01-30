package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.HijoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarHijoComando extends BaseComando {

    public HijoDto hijoDto;
    public JsonObject hijoJson;
    public long _id;

    public ConsultarHijoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoHijo dao = new DaoHijo();
            Hijo hijo = dao.find(_id,Hijo.class);
            this.hijoDto= HijoMapper.mapEntityToDto(hijo);

            hijoJson= Json.createObjectBuilder()
                    .add("id",hijo.get_id())
                    .add("nombre",hijo.get_datoUsuario().get_primerNombre() + " "+ hijo.get_datoUsuario().get_primerApellido())
                    .add("estado",hijo.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Hijo consultado")
                .add("hijo",hijoJson).build();

        return data;
    }
}
