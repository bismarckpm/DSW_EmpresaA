package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
import java.util.List;

public class TelefonoMapper {

    public static List<Telefono> mapDtoToEntityInsert(List<TelefonoDto> telefonos ) throws CustomException
    {
        List<Telefono> telefonos1 = new ArrayList<Telefono>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (TelefonoDto telefonoDto : telefonos) {
            Telefono telefono = new Telefono();
            telefono.set_numero(telefonoDto.getNumero());
            telefono.set_estado(telefonoDto.getEstado());
            Dato_usuario dato_usuario = daoDatoUsuario.find(telefonoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
            telefono.set_datoUsuario(dato_usuario);
            telefonos1.add(telefono);
        }

        return telefonos1;
    }

    public static List<Telefono> mapDtoToEntityUpdate(List<TelefonoDto> telefonos ) throws CustomException
    {
        DaoTelefono daoTelefono=new DaoTelefono();

        List<Telefono> telefonos1 = new ArrayList<Telefono>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (TelefonoDto telefonoDto : telefonos) {
            Telefono telefono = daoTelefono.find(telefonoDto.getId(),Telefono.class);
            telefono.set_numero(telefonoDto.getNumero());
            telefono.set_estado(telefonoDto.getEstado());
            Dato_usuario dato_usuario = daoDatoUsuario.find(telefonoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
            telefono.set_datoUsuario(dato_usuario);
            telefonos1.add(telefono);
        }

        return telefonos1;
    }

    public static List<TelefonoDto> mapEntityToDto(  List<Telefono> telefonos ) throws CustomException {

        List<TelefonoDto> telefonos1 = new ArrayList<TelefonoDto>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (Telefono telefono : telefonos) {
            TelefonoDto telefonoDto = new TelefonoDto();
            telefonoDto.setId(telefono.get_id());
            telefonoDto.setNumero(telefono.get_numero());
            telefonoDto.setEstado(telefono.get_estado());
            telefonoDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(telefono.get_datoUsuario().get_id(), Dato_usuario.class)));
            telefonos1.add(telefonoDto);
        }
        return telefonos1;
    }

    public static TelefonoDto mapEntityToDtoSingle(  Telefono telefono ) throws CustomException {

        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        TelefonoDto telefonoDto = new TelefonoDto();
        telefonoDto.setId(telefono.get_id());
        telefonoDto.setNumero(telefono.get_numero());
        telefonoDto.setEstado(telefono.get_estado());
        telefonoDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(telefono.get_datoUsuario().get_id(), Dato_usuario.class)));
        return telefonoDto;
    }
    
}
