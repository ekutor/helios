package com.co.hsg.innventa.backing.security;

import com.co.hsg.innventa.backing.AppController;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hectsaga
 */
public class SecurityAuditorSession implements SecurityAuditor {

    public SecurityAuditorSession() {

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        AppController app = (AppController) req.getSession().getAttribute("appController");

        String strUrl = req.getRequestURL().toString().toLowerCase();
        if(strUrl.contains("login.xhtml") || strUrl.contains("theme") ||
                strUrl.contains("bootstrap") || strUrl.contains("jquery") ||
                strUrl.contains("faces") ){
          chain.doFilter(request, response);
          return;
        }
       
        if (app != null && app.isLoggedIn()) {
                chain.doFilter(request, response);
        }else{
            String contextPath = req.getContextPath();
            res.sendRedirect(contextPath + "/login.xhtml");
        }

    }

    @Override
    public void destroy() {

    }
}
