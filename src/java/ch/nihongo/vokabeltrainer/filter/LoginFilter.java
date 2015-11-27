 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.nihongo.vokabeltrainer.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oKin
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    private static List<String> allowedURIs;

    public LoginFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getRequestURI();
        HttpSession session = req.getSession(false);

        if (allowedURIs.contains(url)) {

            if (session == null || session.getAttribute("username") == null) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.jsf");
            } else {
                chain.doFilter(request, response);
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        if (allowedURIs == null) {
            allowedURIs = new ArrayList<>();
            allowedURIs.add("/Vokabeltrainer/vt_vocable_test.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_welcome.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_index.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_correct.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_wrong.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_vocable_add.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_vocable_created.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_vocable_settings.jsf");
            allowedURIs.add("/Vokabeltrainer/vt_vocable_statistics.jsf");
        }
    }

}
