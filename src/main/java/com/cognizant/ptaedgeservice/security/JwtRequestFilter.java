package com.cognizant.ptaedgeservice.security;

import com.cognizant.ptaedgeservice.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends BasicAuthenticationFilter {

    private final JwtConverter converter;

    public JwtRequestFilter(AuthenticationManager authenticationManager, JwtConverter converter) {
        super(authenticationManager);
        this.converter = converter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {

            User user = converter.getUserFromToken(authorization);
            System.out.println("User is !!!" +user);
            if (user == null) {
                System.out.println("User is null    !!!!!!!" +user);
                response.setStatus(403); // Forbidden
            } else {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),user.getPassword(), user.getAuthorities());
                System.out.println(token);
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        System.out.println("response  "+response);
        System.out.println("Request:::"+request);
        chain.doFilter(request, response);
    }

}
