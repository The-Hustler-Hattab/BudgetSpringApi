package com.mtattab.emailservice.restcontroller;
import com.mtattab.emailservice.consts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(path = "/v1/api", produces = Constants.JSON)
@Validated
@CrossOrigin(origins = "http://localhost:4200" )
public class BudgetCRUDController {






    @GetMapping("/hello")
    public String helloWorld(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        if (auth.getPrincipal() instanceof Jwt){
            Jwt authPrincipal = (Jwt) auth.getPrincipal();
            String userEmail= (String) authPrincipal.getClaims().get("UserEmail");
            System.out.println(userEmail);
        }
        return "{\"word\":\"hello\"}";
    }
    @GetMapping("/jwt")
    public String getJwtToken() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        return "gwe";
    }

}
