package Vistas;

import ComponentesBeauty.*;
import Conexion.ConexionSQL;
import Conexion.InsertarSQL;

import javax.swing.*;

import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Utilidades.Utilidades.establecerIcono;

/**
 * Created by esva on 21/05/17.
 */
public class NuevoVentaServicio extends JPanel {
    //Componentes
    BeautyTextField txtBuscar;
    BeautyImageButton btnNuevo;
    BeautyImageButton btnEditar;
    BeautyImageButton btnOpciones;
    BeautyImageButton btnFactura;
    BeautyBlackButton btnGuardar;

    BeautyTextField txtFolio;
    BeautyTextField txtMedico;
    BeautyTextField txtServicio;
    BeautyTextField txtPrecio;

    //Lista de busqueda
    public static DefaultListModel dlmBuscador = new DefaultListModel();
    public static BeautyList listaBuscador = new BeautyList();
    public static BeautyScrollPane scrollBuscador = new BeautyScrollPane(listaBuscador, dlmBuscador, 600, 60, 150, 730);

    //Lista de producto
    public static DefaultListModel dlmMedico = new DefaultListModel();
    public static BeautyList listaMedico = new BeautyList();
    public static BeautyScrollPane scrollMedico = new BeautyScrollPane(listaMedico, dlmMedico, 170,420,200, 100);

    //Variables
    String opcionBusqueda = null;
    int folioVenta = 0;
    int idMedico = 0;
    double precio = 0;

    public NuevoVentaServicio() {
        componentes();
        this.setLayout(null);
        this.setBounds(0, 0, 750, 625);
        this.setVisible(true);
    }

    private void componentes() {
        txtBuscar = new BeautyTextField("Buscar...", 600, 0, 150, 30);
        btnNuevo = new BeautyImageButton(establecerIcono("Nuevo", 20, 20), 620, 130, 20, 20);
        btnEditar = new BeautyImageButton(establecerIcono("Editar", 20, 20), 660, 130, 20, 20);
        btnOpciones = new BeautyImageButton(establecerIcono("LlegadaMercancia", 20, 20), 700, 130, 20, 20);
        btnFactura = new BeautyImageButton(establecerIcono("Factura", 20, 20), 740, 130, 20, 20);
        btnGuardar = new BeautyBlackButton("Guardar", 620, 550, 100, 30);

        txtFolio = new BeautyTextField("Folio", 20 ,280, 200, 20);
        txtMedico = new BeautyTextField("Medico",20, 370, 200, 20);
        txtServicio = new BeautyTextField("Servicio", 270, 370, 200, 20);
        txtPrecio = new BeautyTextField("Precio", 530, 370, 200, 20);

        txtFolio.setEnabled(false);
        txtMedico.setEnabled(false);
        txtServicio.setEnabled(false);
        txtPrecio.setEnabled(false);
        btnGuardar.setEnabled(false);


        txtBuscar.addFocusListener(new NuevoVentaServicio.TXTBuscarFocusAdapter());
        txtBuscar.addKeyListener(new NuevoVentaServicio.TXTBuscarKeyAdapter());
        listaBuscador.addMouseListener(new NuevoVentaServicio.ListaBuscarMouseAdapter());
        txtMedico.addFocusListener(new NuevoVentaServicio.TXTNombreProductoFocusAdapter());
        txtMedico.addKeyListener(new NuevoVentaServicio.TXTNombreProductoKeyAdapter());
        listaMedico.addMouseListener(new NuevoVentaServicio.ListaProductoMouseAdapter());
        btnGuardar.addActionListener(new NuevoVentaServicio.BotonGuardar());

        this.add(txtBuscar);
        //this.add(btnNuevo);
        //this.add(btnEditar);
        //this.add(btnOpciones);
        //this.add(btnFactura);
        this.add(btnGuardar);
        this.add(txtFolio);
        this.add(txtMedico);
        this.add(txtServicio);
        this.add(txtPrecio);
    }

    private class TXTBuscarFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollBuscador.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollBuscador.setVisible(false);
        }
    }

    private class TXTBuscarKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtBuscar.getText() + tecla;
            buscarVenta(cadena);
        }
    }

    private class ListaBuscarMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            opcionBusqueda = listaBuscador.getSelectedValue().toString();
            establecerDatos(opcionBusqueda);
            scrollBuscador.setVisible(false);
            txtMedico.setEnabled(true);
            txtServicio.setEnabled(true);
            txtPrecio.setEnabled(true);
            btnGuardar.setEnabled(true);
            txtBuscar.setText("");
        }
    }

    private class TXTNombreProductoFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollMedico.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollMedico.setVisible(false);
        }
    }

    private class TXTNombreProductoKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtMedico.getText() + tecla;
            buscarMedico(cadena);
        }
    }

    private class ListaProductoMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            opcionBusqueda = listaMedico.getSelectedValue().toString();
            txtMedico.setText(opcionBusqueda);
            scrollMedico.setVisible(false);
        }
    }


    private class BotonGuardar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String folioVenta = txtFolio.getText();
            String correo = txtMedico.getText();
            String servicio = txtServicio.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            obtenerIDMedico(correo);

            System.err.println(folioVenta);
            System.err.println(idMedico);
            System.err.println(precio);

            double subtotal = precio;
            double iva = subtotal * 0.16;
            double total = subtotal + iva;

            if (datosCompletos(folioVenta, correo, servicio)) {
                try {
                    InsertarSQL.insertarVentaServicio(folioVenta, 0, idMedico, servicio, precio);
                    InsertarSQL.insertarVentaInfo(folioVenta, subtotal, iva, total);
                    txtFolio.setText("Folio");
                    txtMedico.setText("Medico");
                    txtServicio.setText("Servicio");
                    txtPrecio.setText("Precio");
                    System.out.println("No mames");

                    txtMedico.setEnabled(false);
                    txtServicio.setEnabled(false);
                    txtPrecio.setEnabled(false);
                    btnGuardar.setEnabled(false);
                } catch (Exception nfE) {
                    System.err.println(nfE.getMessage());
                }
            } else {
                System.err.println("lololo");
            }

        }
    }

    /**
     *
     * Métodos
     */


    private  void buscarVenta (String cadena) {
        dlmBuscador.removeAllElements();
        listaBuscador.setVisible(true);
        String sql = "SELECT folio FROM Venta WHERE folio LIKE ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, cadena + "%");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                resultados++;
                String folio = rs.getObject("folio").toString();
                //boolean activo = rs.getBoolean("activo");
                dlmBuscador.addElement(folio);
            }
        } catch (SQLException sqlException) {

        }
    }

    private  void establecerDatos (String opcionBusqueda) {
        String sql = "SELECT folio FROM Venta WHERE folio = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                this.folioVenta = Integer.parseInt(rs.getObject("folio").toString());
                String folio = rs.getObject("folio").toString();
                txtFolio.setText(folio);
                txtFolio.setEnabled(false);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    private void buscarMedico (String cadena) {
        dlmMedico.removeAllElements();
        scrollMedico.setVisible(true);
        String sql = "SELECT correo, rol FROM Usuario WHERE correo LIKE ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, cadena + "%");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                resultados++;
                String correo = rs.getString("correo");
                String rol = rs.getString("rol");
                if(rol.equals("Medico")) {
                    dlmMedico.addElement(correo);
                }
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    private void obtenerIDMedico(String correo) {
        String sql = "SELECT id FROM Usuario WHERE correo = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                idMedico = rs.getInt("id");
            }
        } catch (SQLException sqlException) {

        }
    }

    private boolean datosCompletos(String folio, String correoMedico, String servicio) {
        if (!folio.equals("Folio")) {
            if (!correoMedico.equals("Medico")) {
                if (!servicio.equals("Servicio")) {
                    return true;
                } else {
                    //errorPresentacion.setVisible(true);
                    return false;
                }
            } else {
                //errorProducto.setVisible(true);
                return false;
            }
        } else {
            System.err.println("2345234");
            return false;
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        f.add(scrollBuscador);
        f.add(scrollMedico);
        f.add(new NuevoVentaServicio());
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }


}
