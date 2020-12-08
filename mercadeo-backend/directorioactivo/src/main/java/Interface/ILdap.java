package Interface;

import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.PersonDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;

import javax.naming.NamingException;

public interface ILdap {

    void createPerson(Usuario usuario) throws NamingException;

    PersonDto getPerson(LoginDto loginDto) throws NamingException;

    void deletePerson(Usuario usuario) throws NamingException;

}
