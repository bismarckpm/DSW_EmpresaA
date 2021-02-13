package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class DatoUsuarioMapper {

    public static Dato_usuario mapDtoToEntityInsert(Dato_usuarioDto dato_usuarioDto ) throws CustomException
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

        if (dato_usuarioDto.getCedula() == null || dato_usuarioDto.getCedula().equals("") )
            throw new CustomException("001", "La cedula del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getCedula().length() > 45)
            throw new CustomException("002", "La cedula del usuario es demasiado larga");
        dato_usuario.set_cedula(dato_usuarioDto.getCedula());
        if (dato_usuarioDto.getPrimerNombre() == null || dato_usuarioDto.getPrimerNombre().equals(""))
            throw new CustomException("001", "El primer nombre del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getPrimerNombre().length() > 45)
            throw new CustomException("002", "El primer nombre del usuario es demasiado largo");
        dato_usuario.set_primerNombre(dato_usuarioDto.getPrimerNombre());
        dato_usuario.set_estado("A");
        dato_usuario.set_segundoNombre(dato_usuarioDto.getSegundoNombre());
        if (dato_usuarioDto.getPrimerApellido() == null || dato_usuarioDto.getPrimerApellido().equals(""))
            throw new CustomException("001", "El primer apellido del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getPrimerApellido().length() > 45)
            throw new CustomException("002", "El primer apellido del usuario es demasiado largo");
        dato_usuario.set_primerApellido(dato_usuarioDto.getPrimerApellido());
        if (dato_usuarioDto.getSegundoApellido() == null || dato_usuarioDto.getSegundoApellido().equals(""))
            throw new CustomException("001", "El segundo apellido del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getSegundoApellido().length() > 45)
            throw new CustomException("002", "El segundo apellido del usuario es demasiado largo");
        dato_usuario.set_segundoApellido(dato_usuarioDto.getSegundoApellido());
        if (dato_usuarioDto.getSexo() == null || dato_usuarioDto.getSexo().equals(""))
            throw new CustomException("001", "El sexo del usuario no puede ser nulo ni vacío");
        dato_usuario.set_sexo(dato_usuarioDto.getSexo());
        dato_usuario.set_fechaNacimiento(dato_usuarioDto.getFechaNacimiento());
        if (dato_usuarioDto.getEstadoCivil() == null || dato_usuarioDto.getEstadoCivil().equals(""))
            throw new CustomException("001", "El estado civil del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getEstadoCivil().length()>45)
            throw new CustomException("002", "El estado civil del usuario no puede ser nulo ni vacío");
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

    public static Dato_usuario mapDtoToEntityUpdate(long _id,Dato_usuarioDto dato_usuarioDto ) throws Exception
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

        if (dato_usuarioDto.getCedula() == null || dato_usuarioDto.getCedula().equals("") )
            throw new CustomException("001", "La cedula del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getCedula().length() > 45)
            throw new CustomException("002", "La cedula del usuario es demasiado larga");
        dato_usuario.set_cedula(dato_usuarioDto.getCedula());
        if (dato_usuarioDto.getPrimerNombre() == null || dato_usuarioDto.getPrimerNombre().equals(""))
            throw new CustomException("001", "El primer nombre del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getPrimerNombre().length() > 45)
            throw new CustomException("002", "El primer nombre del usuario es demasiado largo");
        dato_usuario.set_primerNombre(dato_usuarioDto.getPrimerNombre());
        dato_usuario.set_estado("A");
        dato_usuario.set_segundoNombre(dato_usuarioDto.getSegundoNombre());
        if (dato_usuarioDto.getPrimerApellido() == null || dato_usuarioDto.getPrimerApellido().equals(""))
            throw new CustomException("001", "El primer apellido del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getPrimerApellido().length() > 45)
            throw new CustomException("002", "El primer apellido del usuario es demasiado largo");
        dato_usuario.set_primerApellido(dato_usuarioDto.getPrimerApellido());
        if (dato_usuarioDto.getSegundoApellido() == null || dato_usuarioDto.getSegundoApellido().equals(""))
            throw new CustomException("001", "El segundo apellido del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getSegundoApellido().length() > 45)
            throw new CustomException("002", "El segundo apellido del usuario es demasiado largo");
        dato_usuario.set_segundoApellido(dato_usuarioDto.getSegundoApellido());
        if (dato_usuarioDto.getSexo() == null || dato_usuarioDto.getSexo().equals(""))
            throw new CustomException("001", "El sexo del usuario no puede ser nulo ni vacío");
        dato_usuario.set_sexo(dato_usuarioDto.getSexo());
        dato_usuario.set_fechaNacimiento(dato_usuarioDto.getFechaNacimiento());
        if (dato_usuarioDto.getEstadoCivil() == null || dato_usuarioDto.getEstadoCivil().equals(""))
            throw new CustomException("001", "El estado civil del usuario no puede ser nulo ni vacío");
        if (dato_usuarioDto.getEstadoCivil().length()>45)
            throw new CustomException("002", "El estado civil del usuario no puede ser nulo ni vacío");
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

    public static Dato_usuarioDto mapEntityToDto(  Dato_usuario dato_usuario ) throws CustomException {
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto();
        if (dato_usuario == null)
            throw new CustomException("004", "El dato_usuario recibido es nulo");
        DaoLugar daoLugar =new DaoLugar();
        DaoNivel_academico daoNivel_academico = new DaoNivel_academico();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivel_economico daoNivel_economico=new DaoNivel_economico();
        if (dato_usuario.get_id() == 0 || dato_usuario.get_cedula()=="" || dato_usuario.get_primerNombre() == ""
                || dato_usuario.get_primerApellido() == ""|| dato_usuario.get_segundoApellido() == ""
                || dato_usuario.get_sexo() == ""|| dato_usuario.get_estadoCivil() == ""){
            throw new CustomException("001", "Existen atributos inválidos en el dato_usuario");
        }

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
