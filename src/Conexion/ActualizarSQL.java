package Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by esva on 9/05/17.
 */
public class ActualizarSQL {

    public static void actualizarUsuario(String correo, String pathFoto, String rol, String nombre, String apellidoPaterno, String apellidoMaterno, String contraseña, boolean activo, boolean administrador) {
        String sql = "UPDATE Usuario SET path_foto = ?, rol = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, contraseña = ?, activo = ?, administrador = ? WHERE correo = ?";
        String sql2 = "UPDATE Usuario SET  rol = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, contraseña = ?, activo = ?, administrador = ? WHERE correo = ?";
        try {
            if (!pathFoto.equals("src/Iconos/icn_FotoPerfil.png")) {
                PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
                ps.setString(1, pathFoto);
                ps.setString(2, rol);
                ps.setString(3, nombre);
                ps.setString(4, apellidoPaterno);
                ps.setString(5, apellidoMaterno);
                ps.setString(6, contraseña);
                ps.setBoolean(7, activo);
                ps.setBoolean(8, administrador);
                ps.setString(9, correo);
                ps.executeUpdate();
            } else {
                PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql2);
                ps.setString(1, rol);
                ps.setString(2, nombre);
                ps.setString(3, apellidoPaterno);
                ps.setString(4, apellidoMaterno);
                ps.setString(5, contraseña);
                ps.setBoolean(6, activo);
                ps.setBoolean(7, administrador);
                ps.setString(8, correo);
                ps.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException sqlConstraint) {
            System.out.println(sqlConstraint.getMessage());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void actualizarProducto(String nombre, String presentacion, String proveedor, double costo, double precio, String pathFoto, boolean activo) {
        String sql = "UPDATE Producto SET proveedor = ?, costo = ?, precio = ?, path_foto = ?, activo = ? WHERE nombre = ? and presentacion = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, proveedor);
            ps.setDouble(2, costo);
            ps.setDouble(3, precio);
            ps.setString(4, pathFoto);
            ps.setBoolean(5, activo);
            ps.setString(6, nombre);
            ps.setString(7, presentacion);
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException sqlConstraint) {
            System.out.println(sqlConstraint.getMessage());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void actualizarPaciente(String correo, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String religion, String telefono, String telefonoEmergencias, boolean activo, String pathFoto) {
        String sql = "UPDATE Paciente SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, religion = ?, telefono = ?, telefono_emergencias = ?, activo = ?, path_foto = ? WHERE correo = ?";
        String sql2 = "UPDATE Paciente SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, religion = ?, telefono = ?, telefono_emergencias = ?, activo = ? WHERE correo = ?";
        try {
            if (!pathFoto.equals("src/Iconos/icn_FotoPerfil.png")) {
                PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellidoPaterno);
                ps.setString(3, apellidoMaterno);
                ps.setString(4, fechaNacimiento);
                ps.setString(5, religion);
                ps.setString(6, telefono);
                ps.setString(7, telefonoEmergencias);
                ps.setBoolean(8, activo);
                ps.setString(9 , pathFoto);
                ps.setString(10, correo);
                ps.executeUpdate();
            } else {
                PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql2);
                ps.setString(1, nombre);
                ps.setString(2, apellidoPaterno);
                ps.setString(3, apellidoMaterno);
                ps.setString(4, fechaNacimiento);
                ps.setString(5, religion);
                ps.setString(6, telefono);
                ps.setString(7, telefonoEmergencias);
                ps.setBoolean(8, activo);
                ps.setString(9, correo);
                ps.executeUpdate();
            }
        } catch (SQLIntegrityConstraintViolationException sqlConstraint) {
            System.out.println(sqlConstraint.getMessage());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void actualizarVenta(String folio, String formaPago, String tipoCliente, String correoPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, String municipio, String colonia, String calleNumero, boolean activo) {
        String sql = "UPDATE Venta SET forma_pago = ?, tipo_cliente = ?, correo_paciente = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, municipio = ?, colonia = ?, calle_numero = ?, activo = ? WHERE folio = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, formaPago);
            ps.setString(2, tipoCliente);
            ps.setString(3, correoPaciente);
            ps.setString(4, nombre);
            ps.setString(5, apellidoPaterno);
            ps.setString(6, apellidoMaterno);
            ps.setString(7, municipio);
            ps.setString(8, colonia);
            ps.setString(9, calleNumero);
            ps.setBoolean(10, activo);
            ps.setString(11, folio);
            ps.executeUpdate();
        } catch (SQLException sqlException) {

        }
    }

}
