package com.jeefix.adminbutton.services;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** @author xlab, mais */
public class SecurityManager implements AuthenticationManager {
    
    private static final String LOGIN_CTX_NAME = "firstDomainModule";
    
    private static final Logger log = LoggerFactory.getLogger(SecurityManager.class);
    
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        
        String name = auth.getName();
        String credentials = (String) auth.getCredentials();
        
        if(name.equals(credentials)) {
            throw new UsernameNotFoundException("User not found");
        }
        
        List<GrantedAuthority> grantedAuthorities = new LinkedList<GrantedAuthority>() {
            
            private static final long serialVersionUID = -5338396371622589140L;

            {
                add(new SimpleGrantedAuthority("ADMIN"));
            }
        };
        if (log.isDebugEnabled()) {
            log.debug(String.format("User '%s' has been authorized with following authorities %s", name, grantedAuthorities));
        }
        return new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getPrincipal(), grantedAuthorities);
    }
    
}
