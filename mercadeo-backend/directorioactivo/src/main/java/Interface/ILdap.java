package Interface;

import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.PersonDto;

import javax.naming.NamingException;

public interface ILdap {

    void createPerson(PersonDto personDto) throws NamingException;

    PersonDto getPerson(LoginDto loginDto) throws NamingException;

    void changePassword(PersonDto personDto) throws NamingException;

    void authentication(LoginDto loginDto) throws NamingException;

}
