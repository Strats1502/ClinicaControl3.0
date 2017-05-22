package Vistas;

import ComponentesBeauty.*;
import Utilidades.Utilidades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by esva on 21/05/17.
 */
public class BaseDeDatosVista extends JPanel {

        JPanel panelCentral;
        BeautyComboBox comboTabla;
        BeautyImageButton btnActualizar;
        BeautyImageButton btnSubir;
        BeautyImageButton btnEditar;

        public static DefaultListModel dlmTablas = new DefaultListModel();
        public static BeautyList listaTablas = new BeautyList();
        public static BeautyScrollPane scrollTablas = new BeautyScrollPane(listaTablas, dlmTablas, 470, 60, 150, 180);

        public BaseDeDatosVista() {
            componentes();
            this.setLayout(null);
            this.setBounds(0, 0, 750, 625);
            this.setVisible(true);
        }

        private void componentes() {
            panelCentral = new JPanel();
            panelCentral.setBounds(10, 50, 730, 560);
            panelCentral.setLayout(null);
            //panelCentral.setBackground(Color.cyan);

            comboTabla = new BeautyComboBox("Tablas", 290, 10, 200, 20);

            btnSubir = new BeautyImageButton(Utilidades.establecerIcono("Subir", 20, 20), 680, 15, 20, 20);

            dlmTablas.addElement("Usuario");
            dlmTablas.addElement("Producto");
            dlmTablas.addElement("Paciente");
            dlmTablas.addElement("Antecedente");
            dlmTablas.addElement("Antecedente cronico");
            dlmTablas.addElement("Recomendacion");
            dlmTablas.addElement("Venta");
            dlmTablas.addElement("Venta producto");
            dlmTablas.addElement("Venta servicio");
            dlmTablas.addElement("Venta info");
            dlmTablas.addElement("Inventario");
            dlmTablas.addElement("Modificacion");

            comboTabla.addFocusListener(new ComboTablasFocusListener());
            listaTablas.addMouseListener(new ListaTablasMouseAdapter());

            this.add(panelCentral);
            this.add(comboTabla);
            this.add(btnSubir);
        }

        private class ComboTablasFocusListener extends FocusAdapter {
            @Override
            public void focusGained(FocusEvent e) {
                scrollTablas.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                scrollTablas.setVisible(false);
            }
        }

        private class ListaTablasMouseAdapter extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                scrollTablas.setVisible(false);
                String opcion = listaTablas.getSelectedValue().toString();
                if (opcion.equals("Usuario")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Usuario"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Producto")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Producto"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Paciente")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Paciente"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Antecedente")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Antecedente"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Antecedente cronico")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("AntecedenteCronico"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Recomendacion")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Recomendacion"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Venta")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Venta"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Venta producto")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("VentaProducto"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Venta servicio")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("VentaServicio"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Venta info")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("VentaInfo"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Inventario")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Inventario"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                } else if (opcion.equals("Modificacion")) {
                    panelCentral.removeAll();
                    panelCentral.add(new BeautyTable().getBeautyTable("Modificacion"));
                    panelCentral.repaint();
                    panelCentral.revalidate();
                }
            }
        }


    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        f.add(scrollTablas);
        f.add(new BaseDeDatosVista());
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
