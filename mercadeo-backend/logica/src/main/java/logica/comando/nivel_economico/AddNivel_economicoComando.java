package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelEconomicoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddNivel_economicoComando extends BaseComando {

    public Nivel_economicoDto nivel_economicoDto;

    public AddNivel_economicoComando(Nivel_economicoDto nivel_economicoDto) {
        this.nivel_economicoDto = nivel_economicoDto;
    }

    @Override
    public void execute() {

        try {
            DaoNivel_economico dao = Fabrica.crear(DaoNivel_economico.class);
            Nivel_economico nivel_economico = NivelEconomicoMapper.mapDtoToEntityInsert(this.nivel_economicoDto);
            Nivel_economico resul = dao.insert( nivel_economico );
            this.nivel_economicoDto=NivelEconomicoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_economico añadido")
                .add("nivel_economico_id",this.nivel_economicoDto.getId()).build();

        return data;
    }

}
