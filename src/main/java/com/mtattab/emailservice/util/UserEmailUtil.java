package com.mtattab.emailservice.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@UtilityClass
public class UserEmailUtil {



    public static String getUserDetailByClaim(Authentication auth, String claimName){
        if (auth.getPrincipal() instanceof Jwt){
            Jwt authPrincipal = (Jwt) auth.getPrincipal();
            return authPrincipal.getClaims().get(claimName).toString().toLowerCase();
        }
        else throw new RuntimeException("User is not authenticated") ;
    }
}
