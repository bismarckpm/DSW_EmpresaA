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
import java.util.ArrayList;
import java.util.List;

public class EditHijoComando extends BaseComando {

    public long _id;
    public List<HijoDto> hijoDto;

    public EditHijoComando(List<HijoDto> hijoDto) {
        this.hijoDto = hijoDto;
    }

    @Override
    public void execute() {
        try{
            DaoHijo dao = Fabrica.crear(DaoHijo.class);
            List<Hijo> hijo= HijoMapper.mapDtoToEntityUpdate(hijoDto);
            List<Hijo> resul = new ArrayList<>();
            for (Hijo hijox : hijo) {
                resul.add(dao.update(hijox));
            }
            this.hijoDto=HijoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
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
                .add("mensaje","Hijo actualizado")
                .add("id_hijo",salida).build();

        return data;
    }

}
