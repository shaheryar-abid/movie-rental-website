/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

/**
 *
 * @author student
 */
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Date;
import java.util.Map.Entry;
import static javax.crypto.Cipher.SECRET_KEY;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Authenticate {
    SignatureAlgorithm signatureAlgorithm;

    String secretString;

    public Authenticate() {

        signatureAlgorithm = SignatureAlgorithm.HS256;
        
        secretString = Encoders.BASE64.encode("mysecuresecurecodeeeeeeeeeeeeeeeeeeeeeeeeeeeeee".getBytes());

    }

    public String createJWT(String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretString);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder;
        builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        String a = builder.compact();
        System.out.println(a);
        //Builds the JWT and serializes it to a compact, URL-safe string
        return a;
    }

    public Entry<Boolean, String> verify(String jwt) throws UnsupportedEncodingException {
        Jws<Claims> jws = null;
        String username="";
        System.out.println("I am veryfying! :" + jwt);
        try {
            jws = Jwts.parserBuilder() // (1)
                    .setSigningKey("mysecuresecurecode") // (2)
                    .build() // (3)
                    .parseClaimsJws(jwt); // (4)

            System.out.println("we can safely trust the JWT");
            username=jws.getBody().getSubject();
            System.out.println(username);
                  
        } catch (JwtException ex) {       // (5)

            System.out.println(" we *cannot* use the JWT as intended by its creator");
        }
        if (jws == null) {
            Entry entry= new  AbstractMap.SimpleEntry<Boolean, String>(false,"");
            return entry;
        }
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        if (jws.getBody().getExpiration().before(now)) {
           Entry entry= new  AbstractMap.SimpleEntry<Boolean, String>(false,"");
            return entry;
        }

       Entry entry= new  AbstractMap.SimpleEntry<Boolean, String>(true,username);
            return entry;

    }


}
