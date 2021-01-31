package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
import java.util.List;

public class HijoMapper {

    public static List<Hijo> mapDtoToEntityInsert(List<HijoDto> hijos )
    {
        List<Hijo> hijos1 = new ArrayList<Hijo>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (HijoDto hijoDto : hijos) {
            Hijo hijo = new Hijo();
            hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo.set_genero(hijoDto.getGenero());
            hijo.set_estado(hijoDto.getEstado());
            Dato_usuario dato_usuario = daoDatoUsuario.find(hijoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
            hijo.set_datoUsuario(dato_usuario);
            hijos1.add(hijo);
        }

        return hijos1;
    }

    public static List<Hijo> mapDtoToEntityUpdate(List<HijoDto> hijos )
    {
        DaoHijo daoHijo=new DaoHijo();

        List<Hijo> hijos1 = new ArrayList<Hijo>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (HijoDto hijoDto : hijos) {
            Hijo hijo = daoHijo.find(hijoDto.getId(),Hijo.class);
            hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo.set_genero(hijoDto.getGenero());
            hijo.set_estado(hijoDto.getEstado());
            Dato_usuario dato_usuario = daoDatoUsuario.find(hijoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
            hijo.set_datoUsuario(dato_usuario);
            hijos1.add(hijo);
        }

        return hijos1;
    }

    public static List<HijoDto> mapEntityToDto(  List<Hijo> hijos ) throws PruebaExcepcion {

        List<HijoDto> hijos1 = new ArrayList<HijoDto>();
        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        for (Hijo hijo : hijos) {
            HijoDto hijoDto = new HijoDto();
            hijoDto.setId(hijo.get_id());
            hijoDto.setFechaNacimiento(hijo.get_fechaNacimiento());
            hijoDto.setGenero(hijo.get_genero());
            hijoDto.setEstado(hijo.get_estado());
            hijoDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(hijo.get_datoUsuario().get_id(), Dato_usuario.class)));
            hijos1.add(hijoDto);
        }
        return hijos1;
    }

    public static HijoDto mapEntityToDtoSingle( Hijo hijo ) throws PruebaExcepcion {

        DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
        HijoDto hijoDto = new HijoDto();
        hijoDto.setId(hijo.get_id());
        hijoDto.setFechaNacimiento(hijo.get_fechaNacimiento());
        hijoDto.setGenero(hijo.get_genero());
        hijoDto.setEstado(hijo.get_estado());
        hijoDto.setDatoUsuarioDto(DatoUsuarioMapper.mapEntityToDto(daoDatoUsuario.find(hijo.get_datoUsuario().get_id(), Dato_usuario.class)));
        return hijoDto;
    }
    
}
