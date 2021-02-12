package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
import java.util.List;

public class HijoMapper {

    public static List<Hijo> mapDtoToEntityInsert(List<HijoDto> hijos ) throws CustomException
    {
        List<Hijo> hijos1 = new ArrayList<Hijo>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (HijoDto hijoDto : hijos) {
            Hijo hijo = new Hijo();
            if (hijoDto.getGenero() == null || hijoDto.getGenero().equals(""))
                throw new CustomException("001", "El género del hijo no puede ser nulo ni vacío");
            if(hijoDto.getGenero().length() > 45)
                throw new CustomException("002", "El género hijo excede el máximo permitido");
            hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo.set_genero(hijoDto.getGenero());
            hijo.set_estado(hijoDto.getEstado());
            Dato_usuario dato_usuario = daoDatoUsuario.find(hijoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
            hijo.set_datoUsuario(dato_usuario);
            hijos1.add(hijo);
        }

        return hijos1;
    }

    public static List<Hijo> mapDtoToEntityUpdate(List<HijoDto> hijos ) throws CustomException
    {
        DaoHijo daoHijo=new DaoHijo();

        List<Hijo> hijos1 = new ArrayList<Hijo>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (HijoDto hijoDto : hijos) {
            Hijo hijo = daoHijo.find(hijoDto.getId(),Hijo.class);
            if (hijoDto.getGenero() == null || hijoDto.getGenero().equals(""))
                throw new CustomException("001", "El género del hijo no puede ser nulo ni vacío");
            if(hijoDto.getGenero().length() > 45)
                throw new CustomException("002", "El género hijo excede el máximo permitido");
            hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo.set_genero(hijoDto.getGenero());
            hijo.set_estado(hijoDto.getEstado());
            Dato_usuario dato_usuario = daoDatoUsuario.find(hijoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
            hijo.set_datoUsuario(dato_usuario);
            hijos1.add(hijo);
        }

        return hijos1;
    }

    public static List<HijoDto> mapEntityToDto(  List<Hijo> hijos ) throws CustomException {

        List<HijoDto> hijos1 = new ArrayList<HijoDto>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (Hijo hijo : hijos) {
            HijoDto hijoDto = new HijoDto();
            if (hijo == null)
                throw new CustomException("004", "El hijo recibido es nulo");
            if (hijo.get_id() == 0 || hijo.get_genero()=="" ){
                throw new CustomException("001", "Existen atributos inválidos en el hijo");
            }
            hijoDto.setId(hijo.get_id());
            hijoDto.setFechaNacimiento(hijo.get_fechaNacimiento());
            hijoDto.setGenero(hijo.get_genero());
            hijoDto.setEstado(hijo.get_estado());
            hijoDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(hijo.get_datoUsuario().get_id(), Dato_usuario.class)));
            hijos1.add(hijoDto);
        }
        return hijos1;
    }

    public static HijoDto mapEntityToDtoSingle( Hijo hijo ) throws CustomException {

        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        HijoDto hijoDto = new HijoDto();
        if (hijo == null)
            throw new CustomException("004", "El hijo recibida es nulo");
        if (hijo.get_id() == 0 || hijo.get_genero()=="" ){
            throw new CustomException("001", "Existen atributos inválidos en el hijo");
        }
        hijoDto.setId(hijo.get_id());
        hijoDto.setFechaNacimiento(hijo.get_fechaNacimiento());
        hijoDto.setGenero(hijo.get_genero());
        hijoDto.setEstado(hijo.get_estado());
        hijoDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(hijo.get_datoUsuario().get_id(), Dato_usuario.class)));
        return hijoDto;
    }
    
}
