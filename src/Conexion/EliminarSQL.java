package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by esva on 28/04/17.
 */
public class EliminarSQL {

    public static void eliminar (String tabla, String nombreClave, String clave) {
        String sql = "DELETE FROM " + tabla + " WHERE " + nombreClave + " = " + clave + ";";
        try {
            Statement st = ConexionSQL.getConexion().createStatement();
            st.executeUpdate(sql);
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

}
