package com.jeefix.adminbutton.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.jeefix.adminbutton.utils.JsfUtils;

@Controller("loginController")
@Scope("request")
public class LoginController {
    
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private String login;
    private String password;
    
    public String login() {
        log.debug("Attempting to authenticate user '{}'", login);
        HttpServletRequest req = JsfUtils.getCurrentRequest();
        try {
            req.login(getLogin(), getPassword());
            String authorities = req.getUserPrincipal() instanceof UserDetails ? ((UserDetails)req.getUserPrincipal()).getAuthorities().toString(): "[unknown]";
            log.info("User '{}' has been successfuly authenticated with authorities: {}", getLogin(), authorities);
        } catch (ServletException e) {
            log.info(String.format("Unable to authenticate user '%s", getLogin()), e);
            if(e.getCause() instanceof AuthenticationException) {
                handleAuthenticationException((AuthenticationException)e.getCause());
            }else {
                FacesContext.getCurrentInstance().addMessage("Authentication error", new FacesMessage("Unable to authenticate"));
            }
            return "LOGIN_ERROR";
        }
        
        return "DEFAULT";
    }
    
    private void handleAuthenticationException(AuthenticationException e) {
        FacesContext.getCurrentInstance().addMessage("Authentication error", new FacesMessage("Unable to authenticate"));
    }

    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
