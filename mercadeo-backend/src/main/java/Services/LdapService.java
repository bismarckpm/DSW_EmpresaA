package Services;

import DirectoryActive.ImpLdap;
import Dto.LoginDto;
import Dto.PersonDto;
import Interfaces.ILdap;
import Model.Person;


import javax.naming.NamingException;

public class LdapService {

    private ILdap impLdap = new ImpLdap();

    public void createPersonToLdap(PersonDto personDto) throws NamingException {
        impLdap.createPerson(personDto);
    }

    public Person getPersonToLdap(LoginDto loginDto) throws NamingException {
        return impLdap.getPerson(loginDto);
    }

    public void changePasswordToLdap(PersonDto personDto) throws NamingException {
        impLdap.changePassword(personDto);
    }

    public void authenticationToLdap(LoginDto loginDto) throws NamingException {
        impLdap.authentication(loginDto);
    }
}
