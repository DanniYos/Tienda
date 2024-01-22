package org.dan.webapp.apiservlet.headers.filsters;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.dan.webapp.apiservlet.headers.configs.MysqlConn;
import org.dan.webapp.apiservlet.headers.services.ServiceJDBCExeption;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Inject
    @MysqlConn
    private Connection conn;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try(Connection connectionRequest = this.conn){
            if (connectionRequest.getAutoCommit()){
                connectionRequest.setAutoCommit(false);
            }
            try{
//                request.setAttribute("conn", connectionRequest);
                chain.doFilter(request, response);
                connectionRequest.commit();
            }catch (SQLException | ServiceJDBCExeption e){
                connectionRequest.rollback();
                e.printStackTrace();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
