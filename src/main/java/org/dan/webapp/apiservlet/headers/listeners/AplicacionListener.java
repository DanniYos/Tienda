package org.dan.webapp.apiservlet.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.dan.webapp.apiservlet.headers.models.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicializando la app");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "algun valor global de la app");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la app");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo el request");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Inicializando el Request");
        sre.getServletRequest().setAttribute("mensaje", "Guardando un valor para el request");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la session http");
    }
}
