package ComponentesBeauty;

import Conexion.ObtenerDatosTablaSQL;
import InterfazUsuario.Home;
import Vistas.CustomVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by esva on 25/04/17.
 */
public class BeautyTable extends JTable {
    private static JTable tabla;
    private static JPopupMenu pop;



    public static JScrollPane getBeautyTable(String tablaBD) {
        DefaultTableModel dtm = new DefaultTableModel();
        tabla  = new JTable();
        tabla.setModel(dtm);
        pop = new JPopupMenu();


        tabla.setForeground(Color.WHITE);
        tabla.setBackground(new Color(0, 0, 0, 0));
        //tabla.setPreferredScrollableViewportSize(new Dimension(500,70));

        ObtenerDatosTablaSQL.getDatosTabla(dtm, tablaBD);


        tabla.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int renglon = tabla.getSelectedRow();
                mostrarRenglon(renglon);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int renglon = tabla.getSelectedRow();
                mostrarRenglon(renglon);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int renglon = tabla.getSelectedRow();
                mostrarRenglon(renglon);
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int renglon = tabla.getSelectedRow();
                mostrarRenglon(renglon);
            }
        });

        tabla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent k) {
                int renglon = tabla.getSelectedRow();
                mostrarDatos(renglon);
            }

            @Override
            public void keyReleased(KeyEvent k) {
                int renglon = tabla.getSelectedRow();
                mostrarDatos(renglon);
            }

            @Override
            public void keyPressed(KeyEvent k) {
                int renglon = tabla.getSelectedRow();
                mostrarDatos(renglon);
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int renglon = tabla.getSelectedRow();
                mostrarDatos(renglon);
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50,70,700,390);
        scroll.setFont(Home.appleFont());
        scroll.setOpaque(true);
        scroll.setBorder(null);
        scroll.setBackground(new Color(0,0,0,0));
        scroll.getViewport().setBorder(null);
        scroll.getViewport().setOpaque(true);
        scroll.getViewport().setBackground(new Color(0,0,0,0));

        return scroll;
    }

    private static void mostrarDatos(int renglon) {
        System.out.println(tabla.getValueAt(renglon, 0).toString());
    }


    private static void mostrarRenglon(int renglon) {
        System.out.println(tabla.getValueAt(renglon, 0).toString());
        System.out.println(tabla.getValueAt(renglon, 2).toString());
        System.out.println(tabla.getValueAt(renglon, 3).toString());
        System.out.println(tabla.getValueAt(renglon, 4).toString());
        System.out.println(tabla.getValueAt(renglon, 5).toString());
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(800,500);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(getBeautyTable("Producto"));
        f.add(CustomVista.fondo());
        f.setVisible(true);
        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkForTriggerEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                checkForTriggerEvent(e);
            }

            public void checkForTriggerEvent(MouseEvent e) {
                if(e.isPopupTrigger()) {
                    pop.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

}
