package com.mtattab.emailservice.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mtattab.emailservice.consts.Constants.CORS;


@Configuration
public class ProjectSecurityConfig  {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();


        http
                .authorizeRequests()
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                .requestMatchers("/", "about").permitAll()
                .requestMatchers("/actuator/health").permitAll()

                .anyRequest().authenticated()
//
////
//                .anyRequest().permitAll()
                .and()
                .csrf().disable()



//        http.authorizeRequests()
//
//
//
//        .anyRequest().authenticated()
                .oauth2ResourceServer(oauth2ResourceServerCustomizer ->
                        oauth2ResourceServerCustomizer.jwt(jwtCustomizer -> jwtCustomizer.jwtAuthenticationConverter(jwtAuthenticationConverter)))

//               .cors(
//                        corsCustomizer -> corsCustomizer.configurationSource(request -> {
//                            CorsConfiguration config = new CorsConfiguration();
//                            config.setAllowedOrigins(Arrays.asList(CORS));
//                            config.setAllowedMethods(Collections.singletonList("*"));
//                            config.setAllowCredentials(true);
//                            config.setAllowedHeaders(Collections.singletonList("*"));
//                            config.setMaxAge(3600L);
//                            return config;
//                        }
//                        )
//                )
                .oauth2Login()
                        .and().logout().logoutSuccessUrl("/")

;
        return http.build();
    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("*"); // Allow requests from any origin
//        configuration.addAllowedHeader("*"); // Allow all headers
//        configuration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

}
