package logica.comando.datoUsuario;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            dato_usuarioJson= Json.createObjectBuilder()
                    .add("_id",dato_usuario.get_id())
                    .add("_cedula",dato_usuario.get_cedula())
                    .add("_primerNombre", dato_usuario.get_primerNombre())
                    .add("_segundoNombre", dato_usuario.get_segundoNombre())
                    .add("_primerApellido", dato_usuario.get_primerApellido())
                    .add("_segundoApellido", dato_usuario.get_segundoApellido())
                    .add("_sexo", dato_usuario.get_sexo())
                    .add("_fechaNacimiento", dateFormat.format(dato_usuario.get_fechaNacimiento()))
                    .add("_estadoCivil", dato_usuario.get_estadoCivil())
                    .add("_disponibilidadEnLinea", dato_usuario.get_disponibilidadEnLinea())
                    .add("_conCuantasPersonasVive", dato_usuario.get_conCuantasPersonasVive())
                    .add("_medioComunicacion", dato_usuario.get_medioComunicacion())
                    .add("_estado",dato_usuario.get_estado())
                    .add("_nivelEconomico", dato_usuario.get_nivelEconomico().get_id())
                    .add("_lugar", dato_usuario.get_lugar().get_id())
                    .add("_ocupacion", dato_usuario.get_ocupacion().get_id())
                    .add("_nivelAcademico", dato_usuario.get_nivelAcademico().get_id()).build();

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
