package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Nivel_academicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarNivel_academicoComando extends BaseComando {

    public Nivel_academicoDto nivel_academicoDto;
    public JsonObject nivel_academicoJson;
    public long _id;

    public ConsultarNivel_academicoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoNivel_academico dao = new DaoNivel_academico();
            Nivel_academico nivel_academico = dao.find(_id,Nivel_academico.class);
            this.nivel_academicoDto= Nivel_academicoMapper.mapEntityToDto(nivel_academico);

            nivel_academicoJson= Json.createObjectBuilder()
                    .add("id",nivel_academico.get_id())
                    .add("nombre",nivel_academico.get_nivel())
                    .add("estado",nivel_academico.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_academico consultado")
                .add("nivel_academico",nivel_academicoJson).build();

        return data;
    }
}
