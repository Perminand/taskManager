package ru.perminov.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.perminov.dto.UserDto;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.logging.Handler;

@Component
@RequiredArgsConstructor
public class InitialAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsernamePasswordAuthenticationProvider authenticationProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization") == null) {
            String bodyJson = request.getReader().readLine();
            if(bodyJson != null) {
                ObjectMapper mapper = new ObjectMapper();
                UserDto userDto = mapper.readValue(bodyJson, UserDto.class);
                String username = userDto.getUsername();
                String password = userDto.getPassword();
                try {
                    Authentication authentication = new UsernamePasswordAuthentication(username, password);
                    authentication = authenticationProvider.authenticate(authentication);
                    Spring jwt = jwtService.generateJwt(authentication);
                    response.setHeader("Authorization", HeaderValues.BEARER + jwt);
                } catch (BadCredentialsException | ObjectNotFoundException e) {
                    logger.error(e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }



}
