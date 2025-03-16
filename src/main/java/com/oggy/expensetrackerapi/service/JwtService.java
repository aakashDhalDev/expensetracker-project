package com.oggy.expensetrackerapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    public String getKey(){
        return "0c88cc834ac4ada35485db48153817f073d022c45e94690c53ba11f596bef7294c9593b773c0069f280fce1364efcd431c9f4c32f4ae7caf9ef3d1d8426dff877df8ac0d97e88b6103292e6068325bcfa4e09641abd87f9e84b3f6a9026ba61a2c656c27475b7d039b90ba645b2528ebbebab0b76760d11f6b2ad025d30ec04ff8d9631fa76ebd9e70a11170827117436bcd70292c2856b0ca776ab4c3f8a8cc51269e787c679075ef7f1ee03d16c78d1b8888f551667f622df29bff8b9bf5a24e0e0b60ba17f99ae2a8b7167000b7dea50a09e394933121310828d153fd582eee616c4aabe87dd32edc39ce79112d0879dc1c8f706bc308f0ae82b2a0cb9d61";
    }
    public SecretKey getSecretKey(){
        String key = getKey();
        byte[] decode = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(decode);
    }

    public String generateToken(String userEmail){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256).compact();
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build().parseClaimsJws(token).getBody();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
