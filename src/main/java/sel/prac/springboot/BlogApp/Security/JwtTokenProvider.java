package sel.prac.springboot.BlogApp.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import sel.prac.springboot.BlogApp.Exception.BlogAPIException;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiratin-miliseconds}")
    private long jwtExpirationDate;


    //generate jwt token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key())
                .compact();

    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from token
    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    //validate JWT TOKEN
    public boolean validate(String token){
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException malformedJwtException){
            throw  new BlogAPIException("Invalid JWT Token",HttpStatus.BAD_REQUEST);
        }catch (ExpiredJwtException e){
            throw  new BlogAPIException("Expired JWT Token",HttpStatus.BAD_REQUEST);
        }catch (UnsupportedJwtException e){
            throw  new BlogAPIException("Unsupported JWT Token",HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException e){
            throw  new BlogAPIException("JWT clainm string is null ",HttpStatus.BAD_REQUEST);
        }
    }


}
