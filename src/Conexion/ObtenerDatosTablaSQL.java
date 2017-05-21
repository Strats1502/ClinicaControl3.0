package Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * Created by esva on 2/05/17.
 */
public class ObtenerDatosTablaSQL {

    public static void getDatosTabla(DefaultTableModel dtm, String tabla) {
        String sql = "SELECT * FROM " + tabla;
        try {
            Statement st = ConexionSQL.getConexion().createStatement();
            st.executeQuery(sql);

            ResultSet rs = st.getResultSet();
            ResultSetMetaData rsMetaData= rs.getMetaData();
            int numColumnas = rsMetaData.getColumnCount();

            dtm.setColumnIdentifiers(establecerIdentificadores(rsMetaData, numColumnas));

            establecerDatos(rs, dtm, numColumnas);

        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    private static Object[] establecerDatos(ResultSet rs, DefaultTableModel dtm, int numColumnas) {
        Object[] datos = new Object[numColumnas];
        try {
            while(rs.next()) {
                for(int posicion = 0; posicion < numColumnas; posicion++) {
                    datos[posicion] = rs.getObject(posicion + 1);
                }
                dtm.addRow(datos);
            }
            return datos;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }

    private static Object[] establecerIdentificadores(ResultSetMetaData metaDatos, int numColumnas) {
        Object[] identificadores = new Object[numColumnas];
        try {
            for (int posicion = 0; posicion < numColumnas; posicion++) {
                identificadores[posicion] = metaDatos.getColumnName(posicion + 1);
            }
            return identificadores;
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
            return null;
        }
    }

}
