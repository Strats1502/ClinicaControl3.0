package ComponentesBeauty;

import Conexion.ObtenerDatosTablaSQL;
import Utilidades.Utilidades;
import Utilidades.Utilidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by esva on 25/04/17.
 */
public class BeautyTable extends JTable {
     static JTable tabla;
     static JPopupMenu pop;

    public static JScrollPane getBeautyTable(String tablaBD) {
        DefaultTableModel dtm = new DefaultTableModel();
        tabla  = new JTable();
        tabla.setModel(dtm);
        pop = new JPopupMenu();


        tabla.setForeground(Color.BLACK);
        tabla.setBackground(new Color(0, 0, 0, 0));
        //tabla.setPreferredScrollableViewportSize(new Dimension(500,70));

        ObtenerDatosTablaSQL.getDatosTabla(dtm, tablaBD);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0,0,730, 560);
        scroll.setFont(Utilidades.quickSandFont());
        scroll.setOpaque(true);
        scroll.setBorder(null);
        scroll.setBackground(new Color(0,0,0,0));
        scroll.getViewport().setBorder(null);
        scroll.getViewport().setOpaque(true);
        scroll.getViewport().setBackground(new Color(0,0,0,0));


        return scroll;
    }


}
