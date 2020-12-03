package Interfaces;

import Model.Person;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;

public interface ILdap {

    Person getListPerson() throws NamingException;

}
