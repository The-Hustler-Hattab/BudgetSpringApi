package com.mtattab.emailservice.util;

import com.mtattab.emailservice.consts.Constants;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

@UtilityClass
public class UserEmailUtil {



    public static String getUserDetailByClaim(Authentication auth, String claimName){
        if (auth.getPrincipal() instanceof Jwt){
            Jwt authPrincipal = (Jwt) auth.getPrincipal();
            return authPrincipal.getClaims().get(claimName).toString().toLowerCase();
        } else if (auth instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) auth;

            return auth2AuthenticationToken.getPrincipal().getAttributes().get(getGoogleClaim(claimName)).toString().toLowerCase();
        } else throw new RuntimeException("User is not authenticated") ;
    }

    public String getGoogleClaim(String claimName){
        if (claimName.equals(Constants.CLAIM_EMAIL))return "email";
        if (claimName.equals(Constants.CLAIM_FULL_NAME))return "name";

        throw new RuntimeException("Claim is not supported by google OIDC");
    }

}
