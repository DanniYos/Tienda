package org.dan.webapp.apiservlet.headers.filsters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dan.webapp.apiservlet.headers.services.LoginService;
import org.dan.webapp.apiservlet.headers.services.LoginServiceSecionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*", "/productos/form/*", "/productos/eliminar/*", "/usuario/form/*", "/usuario/eliminar/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSecionImpl();
        Optional<String> username = service.getUserName((HttpServletRequest) request);
        if (username.isPresent()){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos no estas autorizado para ingresar a esta pagina");
        }
    }
}
