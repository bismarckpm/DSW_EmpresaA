package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

public class DatoUsuarioMapper {

    public static Dato_usuario mapDtoToEntityInsert(Dato_usuarioDto dato_usuarioDto )
    {
        Dato_usuario dato_usuario = new Dato_usuario();

        DaoLugar daoLugar =new DaoLugar();
        DaoNivel_academico daoNivel_academico = new DaoNivel_academico();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivel_economico daoNivel_economico=new DaoNivel_economico();

        Lugar lugar= daoLugar.find(dato_usuarioDto.getLugarDto().getId(), Lugar.class);
        Nivel_academico nivelAcademico = daoNivel_academico.find(dato_usuarioDto.getNivelAcademicoDto().getId(), Nivel_academico.class);
        Ocupacion ocupacion = daoOcupacion.find(dato_usuarioDto.getOcupacionDto().getId(), Ocupacion.class);
        Nivel_economico nivelEconomico = daoNivel_economico.find(dato_usuarioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);

        dato_usuario.set_cedula(dato_usuarioDto.getCedula());
        dato_usuario.set_primerNombre(dato_usuarioDto.getPrimerNombre());
        dato_usuario.set_estado("A");
        dato_usuario.set_segundoNombre(dato_usuarioDto.getSegundoNombre());
        dato_usuario.set_primerApellido(dato_usuarioDto.getPrimerApellido());
        dato_usuario.set_segundoApellido(dato_usuarioDto.getSegundoApellido());
        dato_usuario.set_sexo(dato_usuarioDto.getSexo());
        dato_usuario.set_fechaNacimiento(dato_usuarioDto.getFechaNacimiento());
        dato_usuario.set_estadoCivil(dato_usuarioDto.getEstadoCivil());
        dato_usuario.set_disponibilidadEnLinea(dato_usuarioDto.getDisponibilidadEnLinea());
        dato_usuario.set_conCuantasPersonasVive(dato_usuarioDto.getConCuantasPersonasVive());
        dato_usuario.set_medioComunicacion(dato_usuarioDto.getMedioComunicacion());
        dato_usuario.set_lugar(lugar);
        dato_usuario.set_nivelAcademico(nivelAcademico);
        dato_usuario.set_nivelEconomico(nivelEconomico);
        dato_usuario.set_ocupacion(ocupacion);

        return dato_usuario;
    }

    public static Dato_usuario mapDtoToEntityUpdate(long _id,Dato_usuarioDto dato_usuarioDto )
    {
        DaoDato_usuario daoDato_usuario=new DaoDato_usuario();

        Dato_usuario dato_usuario = daoDato_usuario.find(_id,Dato_usuario.class);

        DaoLugar daoLugar =new DaoLugar();
        DaoNivel_academico daoNivel_academico = new DaoNivel_academico();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivel_economico daoNivel_economico=new DaoNivel_economico();

        Lugar lugar= daoLugar.find(dato_usuarioDto.getLugarDto().getId(), Lugar.class);
        Nivel_academico nivelAcademico = daoNivel_academico.find(dato_usuarioDto.getNivelAcademicoDto().getId(), Nivel_academico.class);
        Ocupacion ocupacion = daoOcupacion.find(dato_usuarioDto.getOcupacionDto().getId(), Ocupacion.class);
        Nivel_economico nivelEconomico = daoNivel_economico.find(dato_usuarioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);

        dato_usuario.set_cedula(dato_usuarioDto.getCedula());
        dato_usuario.set_primerNombre(dato_usuarioDto.getPrimerNombre());
        dato_usuario.set_estado("A");
        dato_usuario.set_segundoNombre(dato_usuarioDto.getSegundoNombre());
        dato_usuario.set_primerApellido(dato_usuarioDto.getPrimerApellido());
        dato_usuario.set_segundoApellido(dato_usuarioDto.getSegundoApellido());
        dato_usuario.set_sexo(dato_usuarioDto.getSexo());
        dato_usuario.set_fechaNacimiento(dato_usuarioDto.getFechaNacimiento());
        dato_usuario.set_estadoCivil(dato_usuarioDto.getEstadoCivil());
        dato_usuario.set_disponibilidadEnLinea(dato_usuarioDto.getDisponibilidadEnLinea());
        dato_usuario.set_conCuantasPersonasVive(dato_usuarioDto.getConCuantasPersonasVive());
        dato_usuario.set_medioComunicacion(dato_usuarioDto.getMedioComunicacion());
        dato_usuario.set_lugar(lugar);
        dato_usuario.set_nivelAcademico(nivelAcademico);
        dato_usuario.set_nivelEconomico(nivelEconomico);
        dato_usuario.set_ocupacion(ocupacion);

        return dato_usuario;
    }

    public static Dato_usuarioDto mapEntityToDto(  Dato_usuario dato_usuario ) throws PruebaExcepcion {
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto();

        DaoLugar daoLugar =new DaoLugar();
        DaoNivel_academico daoNivel_academico = new DaoNivel_academico();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivel_economico daoNivel_economico=new DaoNivel_economico();

        dato_usuarioDto.setId(dato_usuario.get_id());
        dato_usuarioDto.setCedula(dato_usuario.get_cedula());
        dato_usuarioDto.setPrimerNombre(dato_usuario.get_primerNombre());
        dato_usuarioDto.setEstado("A");
        dato_usuarioDto.setSegundoNombre(dato_usuario.get_segundoNombre());
        dato_usuarioDto.setPrimerApellido(dato_usuario.get_primerApellido());
        dato_usuarioDto.setSegundoApellido(dato_usuario.get_segundoApellido());
        dato_usuarioDto.setSexo(dato_usuario.get_sexo());
        dato_usuarioDto.setFechaNacimiento(dato_usuario.get_fechaNacimiento());
        dato_usuarioDto.setEstadoCivil(dato_usuario.get_estadoCivil());
        dato_usuarioDto.setDisponibilidadEnLinea(dato_usuario.get_disponibilidadEnLinea());
        dato_usuarioDto.setConCuantasPersonasVive(dato_usuario.get_conCuantasPersonasVive());
        dato_usuarioDto.setMedioComunicacion(dato_usuario.get_medioComunicacion());
        dato_usuarioDto.setLugarDto(LugarMapper.mapEntityToDto(daoLugar.find(dato_usuario.get_lugar().get_id(), Lugar.class)));
        dato_usuarioDto.setNivelAcademicoDto(NivelAcademicoMapper.mapEntityToDto(daoNivel_academico.find(dato_usuario.get_nivelAcademico().get_id(), Nivel_academico.class)));
        dato_usuarioDto.setNivelEconomicoDto(NivelEconomicoMapper.mapEntityToDto(daoNivel_economico.find(dato_usuario.get_nivelEconomico().get_id(), Nivel_economico.class)));
        dato_usuarioDto.setOcupacionDto(OcupacionMapper.mapEntityToDto(daoOcupacion.find(dato_usuario.get_ocupacion().get_id(), Ocupacion.class)));

        return dato_usuarioDto;
    }
    
}
