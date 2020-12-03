package Interfaces;

import Dto.LoginDto;
import Dto.PersonDto;
import Model.Person;

import javax.naming.NamingException;

public interface ILdap {

    void createPerson(PersonDto personDto) throws NamingException;

    Person getPerson(LoginDto loginDto) throws NamingException;

    void changePassword(PersonDto personDto) throws NamingException;

    void authentication(LoginDto loginDto) throws NamingException;

}
