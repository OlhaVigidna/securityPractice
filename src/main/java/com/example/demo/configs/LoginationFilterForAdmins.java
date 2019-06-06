package com.example.demo.configs;

import com.example.demo.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginationFilterForAdmins extends AbstractAuthenticationProcessingFilter {


    protected LoginationFilterForAdmins(String defaultFilterProcessesUrl, AuthenticationManager manager) {
        super(defaultFilterProcessesUrl);

        setAuthenticationManager(manager);
    }

    private User temp;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        System.out.println("work");

        User user = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);

        System.out.println(user);

        Authentication authenticate = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        System.out.println(authenticate.isAuthenticated());
        temp = user;

        return authenticate;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        String token = Jwts.builder().setSubject(temp.getName() + "-" + temp.getPassword()).signWith(SignatureAlgorithm.HS512, "yes".getBytes())
                .compact();

        System.out.println(token);
        response.addHeader("token", token);

    }
}
