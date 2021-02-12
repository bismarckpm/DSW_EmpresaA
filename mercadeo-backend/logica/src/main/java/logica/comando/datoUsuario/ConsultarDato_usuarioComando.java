package logica.comando.dato_usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarDato_usuarioComando extends BaseComando {

    public Dato_usuarioDto dato_usuarioDto;
    public JsonObject dato_usuarioJson;
    public long _id;

    public ConsultarDato_usuarioComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoDato_usuario dao = new DaoDato_usuario();
            Dato_usuario dato_usuario = dao.find(_id,Dato_usuario.class);
            this.dato_usuarioDto= DatoUsuarioMapper.mapEntityToDto(dato_usuario);

            dato_usuarioJson= Json.createObjectBuilder()
                    .add("id",dato_usuario.get_id())
                    .add("nombre",dato_usuario.get_primerNombre() + " " + dato_usuario.get_primerApellido())
                    .add("estado",dato_usuario.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Dato_usuario consultado")
                .add("dato_usuario",dato_usuarioJson).build();

        return data;
    }
}
