package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by esva on 6/05/17.
 */
public class ComprobarSQL {

    public static boolean existeCorreo(String correo) {
        String sql = "SELECT * FROM Usuario WHERE correo = ?" ;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    public static boolean existeContraseña(String correo, String contraseña) {
        String sql = "SELECT * FROM Usuario WHERE correo = ? AND contraseña = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    public static boolean activo(String correo, String contraseña) {
        String sql = "SELECT activo FROM Usuario WHERE correo = ? AND contraseña = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(rs.next()) {
                return rs.getBoolean("activo");
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

}
