package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.Nivel_academicoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddNivel_academicoComando extends BaseComando {

    public Nivel_academicoDto nivel_academicoDto;

    public AddNivel_academicoComando(Nivel_academicoDto nivel_academicoDto) {
        this.nivel_academicoDto = nivel_academicoDto;
    }

    @Override
    public void execute() {

        try {
            DaoNivel_academico dao = Fabrica.crear(DaoNivel_academico.class);
            Nivel_academico nivel_academico = Nivel_academicoMapper.mapDtoToEntityInsert(this.nivel_academicoDto);
            Nivel_academico resul = dao.insert( nivel_academico );
            this.nivel_academicoDto=Nivel_academicoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_academico añadido")
                .add("nivel_academico_id",this.nivel_academicoDto.getId()).build();

        return data;
    }

}
