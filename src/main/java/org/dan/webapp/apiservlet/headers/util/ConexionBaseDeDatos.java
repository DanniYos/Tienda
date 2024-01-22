package org.dan.webapp.apiservlet.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=America/Guatemala";
    private static String user = "root";
    private static String password = "4988";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
