package com.example.demo.configs;

import com.example.demo.models.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CheckTokenForAllUsers extends GenericFilterBean {

    private AuthenticationManager authenticationManager;

    public CheckTokenForAllUsers(AuthenticationManager manager) {
        this.authenticationManager = manager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        String token = servletRequest1.getHeader("requestToken");
        if (token != null){
            String decodedToken = Jwts.parser().
                    setSigningKey("yes".getBytes())
                    .parseClaimsJws(token)
                    .getBody().getSubject();

            System.out.println(decodedToken);

            String[] split = decodedToken.split("-");

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(split[0], split[1]));
            if (authenticate.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
