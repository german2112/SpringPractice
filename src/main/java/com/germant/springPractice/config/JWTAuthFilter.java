package com.germant.springPractice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
/*
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter { //Each request is going to pass first from this layer

    private final JWTUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException{
        final String authHeader = request.getHeader(AUTHORIZATION); //Get the authorization field from the request header
        final String userEmail;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer")) { //Validate that there is an Authorization field on the request and that it starts with Bearer
            filterChain.doFilter(request, response); //Always need to reach the diFilter method even if the request is notValid
            return; //If not, then we do not have to do anything else so simply return
        }

        jwtToken = authHeader.substring(7); //If the Authorization field is valid then we take the bearer token within it, in the authorization string, it always starts from index 7
        userEmail = jwtUtils.extractUsername(jwtToken); //Get the username assigned to the token that was passed in the header
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //Validate that the userEmail is not null and that the user is not already authenticated
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail); //Retrieve user Details with the given username or in this case with the user email
            if(jwtUtils.validateToken(jwtToken, userDetails)) { //Validate that the token of the user is valid, if so, give the user a token
                UsernamePasswordAuthenticationToken authToken = //Create the token of the authenticated user
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //Fill the details of the Token with the request (set the token with more information about the request)
                SecurityContextHolder.getContext().setAuthentication(authToken); //Assign the token of the user to the security context holder, now the user is authenticated
            }
        }
        filterChain.doFilter(request, response); //At the end of the process we always need to call the doFilter of the FilterChain interface
    }

}
*/
