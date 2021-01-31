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
import java.util.ArrayList;
import java.util.List;

public class AddHijoComando extends BaseComando {

    public List<HijoDto> hijoDto;

    public AddHijoComando(List<HijoDto> hijoDto) {
        this.hijoDto = hijoDto;
    }

    @Override
    public void execute() {

        try {
            DaoHijo dao = Fabrica.crear(DaoHijo.class);
            List<Hijo> hijo = HijoMapper.mapDtoToEntityInsert(hijoDto);
            List<Hijo> resul = new ArrayList<>();
            for (Hijo hijox : hijo) {
                resul.add(dao.insert(hijox));
            }
            this.hijoDto = HijoMapper.mapEntityToDto(resul);
        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        String salida = "";
        for(HijoDto hdto : hijoDto){
            salida= salida + hdto.getId() + " - ";
        }
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Hijo añadido")
                .add("hijo_id",salida).build();

        return data;
    }

}
