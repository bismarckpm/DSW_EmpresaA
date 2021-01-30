package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.HijoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddHijoComando extends BaseComando {

    public HijoDto hijoDto;

    public AddHijoComando(HijoDto hijoDto) {
        this.hijoDto = hijoDto;
    }

    @Override
    public void execute() {

        try {
            DaoHijo dao = Fabrica.crear(DaoHijo.class);
            Hijo hijo = HijoMapper.mapDtoToEntityInsert(this.hijoDto);
            Hijo resul = dao.insert( hijo );
            this.hijoDto=HijoMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Hijo añadido")
                .add("hijo_id",this.hijoDto.getId()).build();

        return data;
    }

}
