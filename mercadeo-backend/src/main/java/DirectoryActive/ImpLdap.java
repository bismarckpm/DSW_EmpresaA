package DirectoryActive;

import Dto.LoginDto;
import Dto.PersonDto;
import Interfaces.ILdap;
import Model.Login;
import Model.Person;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ImpLdap implements ILdap {

    Configuration confLdap =  new Configuration("ldap://localhost:10389","secret");

    @Override
    public void createPerson(PersonDto personDto) throws NamingException {

        DirContext connection = confLdap.connectLDAP();
        try {

            Attribute oc = new BasicAttribute( "objectClass" );
            oc.add( "top" );
            oc.add( "person" );
            SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmm" );
            BasicAttributes entry = new BasicAttributes();
            entry.put( oc );
            entry.put( new BasicAttribute( "cn", personDto.getEmail() ) );
            entry.put( new BasicAttribute( "sn", personDto.getEmail() ) );
            entry.put( new BasicAttribute( "userpassword", personDto.getPassword() ) );
            entry.put( new BasicAttribute( "pwdLastSuccess", format.format( new Date() ) + "Z" ) );
            connection.createSubcontext( String.format( "cn=%s" + "," + "ou=users,o=mercadeo,ou=system", personDto.getPassword()), entry );

        }catch(Exception exception) {

            exception.printStackTrace();

        }finally {
            confLdap.disconnectLDAP();
        }

    }

    @Override
    public Person getPerson(LoginDto loginDto) throws NamingException {

        DirContext connection = confLdap.connectLDAP();

        Person person = new Person();

        try {

            SearchControls searcCon = new SearchControls();
            searcCon.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results =
                    connection.search( "ou=users,o=mercadeo,ou=system", String.format("cn=%s", loginDto.getEmail()), searcCon );
            if ( results != null ){
                while ( results.hasMore()) {
                    SearchResult res = (SearchResult) results.next();
                    Attributes atbs = res.getAttributes();
                    Attribute atb = atbs.get("cn");
                    String name = (String) atb.get();
                    person.setName(name);
                }
            }

        }catch (Exception exception){

            exception.printStackTrace();

        }finally{

            confLdap.disconnectLDAP();

        }
        return person;
    }

    @Override
    public void changePassword(PersonDto personDto) throws NamingException {

        DirContext connection = confLdap.connectLDAP();

        try{

            ModificationItem[] modificationItems = new ModificationItem[2];
            modificationItems[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                                    new BasicAttribute( "userpassword", personDto.getPassword()));

           // modificationItems[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
             //                                       new BasicAttribute( "description", "NUEVO"));

            connection.modifyAttributes(String.format("cn=%s" + "," + "ou=users,o=mercadeo,ou=system", personDto.getEmail()), modificationItems );

        }catch(Exception exception){

            exception.printStackTrace();

        }finally{

            confLdap.disconnectLDAP();

        }
    }

    @Override
    public void authentication(LoginDto loginDto) throws NamingException {

        DirContext connection = confLdap.connectLDAP();

        try{

            SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmm" );
            ModificationItem[] modificationItems = new ModificationItem[ 2 ];
            modificationItems[ 0 ] = new ModificationItem( DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(
                    "pwdLastSuccess", format.format( new Date() ) + "Z" ) );
            connection.modifyAttributes(String.format("cn=%s" + "," +  "ou=users,o=mercadeo,ou=system", loginDto.getEmail()), modificationItems );

        }catch(Exception exception){

            exception.printStackTrace();

        }finally{

            confLdap.disconnectLDAP();

        }
    }
}
