package ucab.dsw.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.entidades.Usuario;

import java.security.Key;
import java.util.Date;


public class Jwt {

    private static Key KEY= MacProvider.generateKey();



    public static String generarToken(long _id){


        try{
            String token=Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,KEY)
                .setSubject(String.valueOf(_id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10800000))
                .compact();

            return token;

        }catch(Exception ex){
            return ex.getMessage();
        }

    }

    public static boolean verificarToken(String token){

        Jws<Claims> jwt;
        try{

            jwt= Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            long token_subject_web= Long.parseLong(jwt.getBody().getSubject());
            DaoUsuario dao=new DaoUsuario();
            Usuario usuario=dao.find(token_subject_web,Usuario.class);
            long token_subject_bd = Long.parseLong(Jwts.parser().setSigningKey(KEY).parseClaimsJws(usuario.get_token()).getBody().getSubject());

            if(token_subject_bd==token_subject_web){
                return true;
            }
            else{
                return false;
            }


        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
