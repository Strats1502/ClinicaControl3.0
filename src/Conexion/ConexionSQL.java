package Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * Created by Juan José Estrada Valtierra on 26/04/17.
 */
public class ConexionSQL {

    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/ClinicaControl", "root", null);
        } catch (ClassNotFoundException classNotFound) {
            System.err.println(classNotFound.getMessage());
            return null;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }

}
