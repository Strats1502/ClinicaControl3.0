package Vistas;

import javax.swing.*;
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
public class NuevoVentaProducto  extends JPanel {
    //Componentes
    BeautyTextField txtBuscar;
    BeautyImageButton btnNuevo;
    BeautyImageButton btnEditar;
    BeautyImageButton btnOpciones;
    BeautyImageButton btnFactura;
    BeautyBlackButton btnGuardar;

    BeautyTextField txtFolio;
    BeautyTextField txtNombreProducto;
    BeautyComboBox comboPresentacion;
    BeautyTextField txtCantidad;

    //Lista de busqueda
    public static DefaultListModel dlmBuscador = new DefaultListModel();
    public static BeautyList listaBuscador = new BeautyList();
    public static BeautyScrollPane scrollBuscador = new BeautyScrollPane(listaBuscador, dlmBuscador, 750, 60, 150, 730);

    //Lista de producto
    public static DefaultListModel dlmProducto = new DefaultListModel();
    public static BeautyList listaProducto = new BeautyList();
    public static BeautyScrollPane scrollProducto = new BeautyScrollPane(listaProducto, dlmProducto, 170,420,200, 100);

    //Lista de producto
    public static DefaultListModel dlmPresentacion = new DefaultListModel();
    public static BeautyList listaPresentacion = new BeautyList();
    public static BeautyScrollPane scrollPresentacion = new BeautyScrollPane(listaPresentacion,dlmPresentacion, 470,420,100, 70);

    public static BeautyErrorMessage errorCantidad = new BeautyErrorMessage("Ingresa una cantidad numerica");
    public static BeautyErrorMessage errorProducto= new BeautyErrorMessage("Selecciona un producto");
    public static BeautyErrorMessage errorPresentacion = new BeautyErrorMessage("Selecciona un tipo de presentacion");

    //Variables
    String opcionBusqueda = null;
    int folioVenta = 0;
    int idProducto = 0;
    double precio = 0;


    public NuevoVentaProducto() {
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
        txtNombreProducto = new BeautyTextField("Producto",20, 370, 200, 20);
        comboPresentacion = new BeautyComboBox("Presentación", 270, 370, 200, 20);
        txtCantidad = new BeautyTextField("Cantidad", 530, 370, 200, 20);

        txtFolio.setEnabled(false);
        txtNombreProducto.setEnabled(false);
        comboPresentacion.setEnabled(false);
        txtCantidad.setEnabled(false);
        btnGuardar.setEnabled(false);

        txtBuscar.addFocusListener(new TXTBuscarFocusAdapter());
        txtBuscar.addKeyListener(new TXTBuscarKeyAdapter());
        listaBuscador.addMouseListener(new ListaBuscarMouseAdapter());
        txtNombreProducto.addFocusListener(new TXTNombreProductoFocusAdapter());
        txtNombreProducto.addKeyListener(new TXTNombreProductoKeyAdapter());
        listaProducto.addMouseListener(new ListaProductoMouseAdapter());
        comboPresentacion.addFocusListener(new ComboPresentacionFocusAdapter());
        listaPresentacion.addMouseListener(new ListaPresentacionMouseAdapter());
        btnGuardar.addActionListener(new BotonGuardar());

        this.add(txtBuscar);
        //this.add(btnNuevo);
        //this.add(btnEditar);
        //this.add(btnOpciones);
        //this.add(btnFactura);
        this.add(btnGuardar);
        this.add(txtFolio);
        this.add(txtNombreProducto);
        this.add(comboPresentacion);
        this.add(txtCantidad);
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
            txtNombreProducto.setEnabled(true);
            comboPresentacion.setEnabled(true);
            txtCantidad.setEnabled(true);
            btnGuardar.setEnabled(true);
            txtBuscar.setText("");
        }
    }

    private class TXTNombreProductoFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollProducto.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollProducto.setVisible(false);
        }
    }

    private class TXTNombreProductoKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtNombreProducto.getText() + tecla;
            buscarProducto(cadena);
        }
    }

    private class ListaProductoMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            opcionBusqueda = listaProducto.getSelectedValue().toString();
            txtNombreProducto.setText(opcionBusqueda);
            scrollProducto.setVisible(false);
            buscarPresentacion(opcionBusqueda);
        }
    }

    private class ComboPresentacionFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollPresentacion.setVisible(true);
        }

        @Override
        public  void focusLost(FocusEvent e) {
            scrollPresentacion.setVisible(false);
        }
    }

    private class ListaPresentacionMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String opcionPresentacion = listaPresentacion.getSelectedValue().toString();
            comboPresentacion.setText(opcionPresentacion);
            scrollPresentacion.setVisible(false);
        }
    }

    private class BotonGuardar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String folioVenta = txtFolio.getText();
            String nombreProducto = txtNombreProducto.getText();
            String presentacionProducto = comboPresentacion.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            obtenerIDProducto(nombreProducto, presentacionProducto);

            System.err.println(folioVenta);
            System.err.println(idProducto);
            System.err.println(precio);

            double subtotal = cantidad * precio;
            double iva = subtotal * 0.16;
            double total = subtotal + iva;

            if (datosCompletos(folioVenta, nombreProducto, presentacionProducto)) {
                try {
                    InsertarSQL.insertarVentaProducto(folioVenta, idProducto, cantidad);
                    InsertarSQL.insertarVentaInfo(folioVenta, subtotal, iva, total);
                    txtFolio.setText("Folio");
                    txtNombreProducto.setText("Producto");
                    comboPresentacion.setText("Presentación");
                    txtCantidad.setText("Cantidad");
                    System.out.println("No mames");

                    txtNombreProducto.setEnabled(false);
                    comboPresentacion.setEnabled(false);
                    txtCantidad.setEnabled(false);
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

    private void buscarProducto (String cadena) {
        dlmProducto.removeAllElements();
        listaProducto.setVisible(true);
        String sql = "SELECT DISTINCT nombre,activo FROM Producto WHERE nombre LIKE ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, cadena + "%");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                resultados++;
                String nombre = rs.getObject("nombre").toString();
                boolean activo = rs.getBoolean("activo");
                if(activo) {
                    dlmProducto.addElement(nombre);
                }
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    private void buscarPresentacion(String opcionBusqueda) {
        dlmPresentacion.removeAllElements();
        String sql = "SELECT presentacion FROM Producto WHERE nombre = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String presentacion = rs.getObject("presentacion").toString();
                dlmPresentacion.addElement(presentacion);
            }
        } catch (SQLException sqlException) {

        }
    }

    private void obtenerIDProducto(String nombreProducto, String presentacionProducto) {
        String sql = "SELECT id, precio FROM Producto WHERE nombre = ? and presentacion = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, nombreProducto);
            ps.setString(2, presentacionProducto);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                idProducto = rs.getInt("id");
                precio = rs.getDouble("precio");
            }
        } catch (SQLException sqlException) {

        }
    }

    private boolean datosCompletos(String folio, String nombreProducto, String presentacion) {
        if (!folio.equals("Folio")) {
            if (!nombreProducto.equals("Producto")) {
                if (!presentacion.equals("Presentación")) {
                    return true;
                } else {
                    errorPresentacion.setVisible(true);
                    return false;
                }
            } else {
                errorProducto.setVisible(true);
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
        f.add(scrollProducto);
        f.add(scrollPresentacion);
        f.add(new NuevoVentaProducto());
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
