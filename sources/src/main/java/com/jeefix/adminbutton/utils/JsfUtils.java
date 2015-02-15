package com.jeefix.adminbutton.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


public class JsfUtils {
    public static HttpServletRequest getCurrentRequest() {
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest());
    }
}
