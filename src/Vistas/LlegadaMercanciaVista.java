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
import static Utilidades.Utilidades.establecerImagenPath;


/**
 * Created by esva on 21/05/17.
 */
public class LlegadaMercanciaVista extends JPanel {
    //Componentes
    BeautyTextField txtBuscar;
    BeautyImageButton btnNuevo;
    BeautyImageButton btnEditar;
    BeautyImageButton btnLlegadaMercancia;
    BeautyBlackButton btnGuardar;
    BeautyImageButton btnImagenProducto;
    BeautyComboBox comboBoxPresentacion;
    BeautyTextField txtNombre;
    BeautyTextField txtCantidad;

    BeautyComboBox comboPresentacion;



    //Lista para buscador
    public static DefaultListModel dlmBuscador = new DefaultListModel();
    public static BeautyList listaBuscador = new BeautyList();
    public static BeautyScrollPane scrollBuscador = new BeautyScrollPane(listaBuscador, dlmBuscador,750, 60, 150, 730);

    //Lista de producto
    public static DefaultListModel dlmPresentacion = new DefaultListModel();
    public static BeautyList listaPresentacion = new BeautyList();
    public static BeautyScrollPane scrollPresentacion = new BeautyScrollPane(listaPresentacion,dlmPresentacion, 470,420,100, 70);


    //Errores
    public static BeautyErrorMessage errorFormato = new BeautyErrorMessage("Ingresa un carácter numérico...");
    public static BeautyErrorMessage errorOpcion= new BeautyErrorMessage("No has seleccionado algun elemento...");

    //Variables
    String opcionBusqueda = null;
    int id = 0;

    //Constructor
    public LlegadaMercanciaVista() {
        componentes();
        this.setLayout(null);
        this.setBounds(0, 0, 750, 625);
        this.setVisible(true);
    }

    //Inicia componentes
    private void componentes() {
        //Componentes
        btnImagenProducto = new BeautyImageButton(establecerIcono("Producto", 200, 200), 290, 10, 200, 200);
        comboBoxPresentacion = new BeautyComboBox("Presentación", 290, 250, 200, 20);
        txtNombre = new BeautyTextField("Nombre", 290, 340, 200, 20);
        txtCantidad = new BeautyTextField("Cantidad", 290, 470, 200, 20);
        txtBuscar = new BeautyTextField("Buscar...", 600, 0, 150, 30);
        btnGuardar = new BeautyBlackButton("Guardar", 620, 550, 100, 30);
        btnNuevo = new BeautyImageButton(establecerIcono("Nuevo", 20, 20), 620, 130, 20, 20);
        btnEditar = new BeautyImageButton(establecerIcono("Editar", 20, 20), 660, 130, 20, 20);
        btnLlegadaMercancia = new BeautyImageButton(establecerIcono("LlegadaMercancia", 20, 20), 700, 130, 20, 20);

        comboBoxPresentacion.setEnabled(false);
        txtNombre.setEnabled(false);
        btnGuardar.setEnabled(false);

        txtBuscar.addKeyListener(new Buscador());
        txtBuscar.addFocusListener(new BuscadorFocus());
        listaBuscador.addMouseListener(new ListaBuscadorElemento());
        btnNuevo.addActionListener(new BotonNuevo());
        btnGuardar.addActionListener(new BotonGuardar());

        this.add(btnImagenProducto);
        this.add(comboBoxPresentacion);
        this.add(txtNombre);
        this.add(txtCantidad);
        this.add(txtBuscar);
        //this.add(btnNuevo);
        //this.add(btnEditar);
        //this.add(btnLlegadaMercancia);
        this.add(btnGuardar);
    }

    /**
     * Clases de accion a componentes
     */
    private class Buscador extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtBuscar.getText() + tecla;
            buscarProducto(cadena);
        }
    }

    private class ListaBuscadorElemento extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            opcionBusqueda = listaBuscador.getSelectedValue().toString();
            establecerDatos(opcionBusqueda);
            scrollBuscador.setVisible(false);
            btnGuardar.setEnabled(true);
            txtBuscar.setText("");
        }
    }

    private class BuscadorFocus extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollBuscador.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollBuscador.setVisible(false);
        }
    }

    private class BotonGuardar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selecciono()) {
                try {
                    String nombre = txtNombre.getText();
                    String presentacion = comboBoxPresentacion.getText();
                    int cantidad = Integer.parseInt(txtCantidad.getText());
                    buscarID(nombre, presentacion);
                    InsertarSQL.insertarInventario(id, cantidad);
                    btnImagenProducto.setIcon(establecerIcono("Producto", 200, 200));
                    txtNombre.setText("Nombre");
                    txtCantidad.setText("Cantidad");
                    opcionBusqueda = null;
                    btnGuardar.setEnabled(false);
                } catch (NumberFormatException numberException) {
                    errorFormato.setVisible(true);
                }
            } else {
                errorOpcion.setVisible(true);
            }
        }
    }

    private class BotonNuevo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    /**
     * Métodos
     */
    private boolean selecciono(){
        if(opcionBusqueda != null) {
            return true;
        } else {
            return false;
        }
    }

    private void buscarID(String nombre, String presentacion) {
        String sql = "SELECT id FROM Producto WHERE nombre = ? and presentacion = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, presentacion);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                id = Integer.parseInt(rs.getObject("id").toString());
            }
        } catch (SQLException sqlException) {

        }
    }

    private void buscarProducto (String cadena) {
        dlmBuscador.removeAllElements();
        scrollBuscador.setVisible(true);
        String sql = "SELECT DISTINCT nombre, activo FROM Producto WHERE nombre LIKE ?";
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
                    dlmBuscador.addElement(nombre);
                } else {

                }
            }
        } catch (SQLException sqlException) {

        }
    }

    private void establecerDatos (String opcionBusqueda) {
        String sql = "SELECT * FROM Producto WHERE nombre = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                String pathFoto = rs.getObject("path_foto").toString();
                String presentacion = rs.getObject("presentacion").toString();
                String nombre = rs.getObject("nombre").toString();

                //Le pone los datos a los componentes de la opcion seleccionada
                btnImagenProducto.setIcon(establecerImagenPath(pathFoto, 200, 200));
                comboBoxPresentacion.setText(presentacion);
                txtNombre.setText(nombre);

                //Evita que se editen los datos
                //btnImagenProducto.setEnabled(false);
                comboBoxPresentacion.setEnabled(false);
                txtNombre.setEnabled(false);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }


    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        f.add(scrollBuscador);
        f.add(errorFormato);
        f.add(errorOpcion);
        f.add(new LlegadaMercanciaVista());
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

}
