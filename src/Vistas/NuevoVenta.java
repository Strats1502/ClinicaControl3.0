package Vistas;

import javax.swing.*;
import ComponentesBeauty.*;
import Conexion.ActualizarSQL;
import Conexion.ConexionSQL;
import Conexion.InsertarSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Utilidades.Utilidades.establecerIcono;

/**
 * Created by esva on 21/05/17.
 */
public class NuevoVenta extends JPanel {
    //Componentes
    BeautyTextField txtBuscar;
    BeautyImageButton btnNuevo;
    BeautyImageButton btnEditar;
    BeautyImageButton btnOpciones;
    BeautyImageButton btnFactura;
    BeautyBlackButton btnGuardar;

    BeautyTextField txtFolio;
    BeautyComboBox  comboTipoCliente;
    BeautyComboBox comboFormaPago;
    BeautyTextField txtPaciente;

    BeautyTextField txtNombre;
    BeautyTextField txtApellidoPaterno;
    BeautyTextField txtApellidoMaterno;

    BeautyTextField txtMunicipio;
    BeautyTextField txtColonia;
    BeautyTextField txtCalleNumero;

    BeautyCheckbox checkActivo;

    //Lista de busqueda
    //public static DefaultListModel dlmBuscador = new DefaultListModel();
    //public static BeautyList listaBuscador = new BeautyList(dlmBuscador, 300, 150, 300, 140);

    //Lista tipo de cliente
    public static DefaultListModel dlmTipoCliente = new DefaultListModel();
    public static BeautyList listaTipoCliente = new BeautyList();
    public static BeautyScrollPane scrollTipoCliente = new BeautyScrollPane(listaTipoCliente,dlmTipoCliente,470, 420, 100, 40);

    //Lista de paciente
    public static DefaultListModel dlmPaciente = new DefaultListModel();
    public static BeautyList listaPaciente = new BeautyList();
    public static BeautyScrollPane scrollPaciente = new BeautyScrollPane(listaPaciente, dlmPaciente, 680, 420, 200, 200);

    //Lista forma de pago
    public static DefaultListModel dlmFormaPago = new DefaultListModel();
    public static BeautyList listaFormaPago = new BeautyList();
    public static BeautyScrollPane scrollFormaPago = new BeautyScrollPane(listaFormaPago, dlmFormaPago,  220, 420, 100,35);

    public static BeautyErrorMessage errorFolio = new BeautyErrorMessage("No ingresaste un folio");
    public static BeautyErrorMessage errorTipoPago= new BeautyErrorMessage("Selecciona un tipo de pago");
    public static BeautyErrorMessage errorTipoPaciente = new BeautyErrorMessage("Selecciona un tipo de paciente");
    public static BeautyErrorMessage errorDatosUsuario = new BeautyErrorMessage("Datos usuario incompletos");
    public static BeautyErrorMessage errorDatosLocalizacion = new BeautyErrorMessage("Datos localización incompletos");
    public static BeautyErrorMessage errorBusqueda = new BeautyErrorMessage("No has seleccionado una opción");


    //Variables
    String opcionBusqueda = null;
    int id = 0;
    boolean editando = false;
    int idUsuario = 0;


    public NuevoVenta(int idUsuario) {
        this.idUsuario = idUsuario;
        componentes();
        this.setLayout(null);
        this.setBounds(0, 0, 750, 625);
        this.setVisible(true);
    }

    private void componentes() {
        txtBuscar = new BeautyTextField("Buscar...", 300, 120, 300, 30);
        btnNuevo = new BeautyImageButton(establecerIcono("Nuevo", 20, 20), 620, 130, 20, 20);
        btnEditar = new BeautyImageButton(establecerIcono("Editar", 20, 20), 660, 130, 20, 20);
        btnOpciones = new BeautyImageButton(establecerIcono("LlegadaMercancia", 20, 20), 700, 130, 20, 20);
        btnFactura = new BeautyImageButton(establecerIcono("Factura", 20, 20), 740, 130, 20, 20);
        btnGuardar = new BeautyBlackButton("Guardar", 620, 550, 100, 30);

        txtFolio = new BeautyTextField("Folio", 60, 210, 200, 30);
        comboFormaPago = new BeautyComboBox("Forma de pago", 20, 370, 200, 20);
        comboTipoCliente = new BeautyComboBox("Tipo cliente", 270, 370, 200, 20);
        txtPaciente = new BeautyTextField("Paciente", 530, 370, 200, 20);

        txtNombre = new BeautyTextField("Nombre", 20 ,280, 200, 20);
        txtApellidoPaterno = new BeautyTextField("Apellido paterno", 270, 280, 200, 20);
        txtApellidoMaterno = new BeautyTextField("Apellido materno", 530, 280, 200, 20);

        txtMunicipio = new BeautyTextField("Municipio",20, 470, 200, 20);
        txtColonia = new BeautyTextField("Colonia", 270, 470, 200, 20);
        txtCalleNumero = new BeautyTextField("Calle/Numero", 530, 470, 200, 20);

        checkActivo = new BeautyCheckbox("Activo", 60, 450, 200);

        txtPaciente.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellidoPaterno.setEnabled(false);
        txtApellidoMaterno.setEnabled(false);

        dlmTipoCliente.addElement("Paciente");
        dlmTipoCliente.addElement("Normal");

        dlmFormaPago.addElement("Crédito");
        dlmFormaPago.addElement("Contado");

        listaTipoCliente.addMouseListener(new ListaTipoClienteMouseAdapter());
        comboTipoCliente.addFocusListener(new ComboTipoClienteFocusAdapter());
        listaFormaPago.addMouseListener(new ListaFormaPagoMouseAdapter());
        comboFormaPago.addFocusListener(new ComboFormaPagoFocusAdapter());
        txtBuscar.addKeyListener(new TXTBuscarKeyAdapter());
        txtBuscar.addFocusListener(new TXTBuscarFocusAdapter());
        //listaBuscador.addMouseListener(new ListaBuscarMouseAdapter());
        txtPaciente.addFocusListener(new TXTCorreoPacienteFocusAdapter());
        txtPaciente.addKeyListener(new TXTCorreoPacienteKeyAdapter());
        listaPaciente.addMouseListener(new ListaCorreoPacienteMouseAdapter());
        btnGuardar.addActionListener(new BotonGuardarActionListener());
        btnNuevo.addActionListener(new BotonNuevoActionListener());
        btnEditar.addActionListener(new BotonEditarActionListener());

        //this.add(txtBuscar);
        //this.add(btnNuevo);
        //this.add(btnEditar);
        //this.add(btnOpciones);
        //this.add(btnFactura);
        this.add(btnGuardar);
        //this.add(txtFolio);
        this.add(comboFormaPago);
        this.add(comboTipoCliente);
        this.add(txtPaciente);
        this.add(txtNombre);
        this.add(txtApellidoPaterno);
        this.add(txtApellidoMaterno);
        this.add(txtMunicipio);
        this.add(txtColonia);
        this.add(txtCalleNumero);
        //this.add(checkActivo);
    }

    private class TXTBuscarFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            //listaBuscador.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            //listaBuscador.setVisible(false);
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
            //opcionBusqueda = listaBuscador.getSelectedValue().toString();
            establecerDatos(opcionBusqueda);
            //listaBuscador.setVisible(false);
            editando = false;
        }
    }

    private class ComboFormaPagoFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollFormaPago.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollFormaPago.setVisible(false);
        }
    }

    private class ListaFormaPagoMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String formaPago = listaFormaPago.getSelectedValue().toString();
            comboFormaPago.setText(formaPago);
            scrollFormaPago.setVisible(false);
        }
    }

    private class ComboTipoClienteFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollTipoCliente.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollTipoCliente.setVisible(false);
        }
    }

    private class ListaTipoClienteMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String tipoCliente = listaTipoCliente.getSelectedValue().toString();
            comboTipoCliente.setText(tipoCliente);
            scrollTipoCliente.setVisible(false);


            txtPaciente.setText("Paciente");
            txtNombre.setText("Nombre");
            txtApellidoPaterno.setText("Apellido paterno");
            txtApellidoMaterno.setText("Apellido materno");

            if (tipoCliente.equals("Paciente")) {
                txtPaciente.setEnabled(true);
                txtNombre.setEnabled(false);
                txtApellidoPaterno.setEnabled(false);
                txtApellidoMaterno.setEnabled(false);
            } else if (tipoCliente.equals("Normal")) {
                txtPaciente.setEnabled(false);
                txtNombre.setEnabled(true);
                txtApellidoPaterno.setEnabled(true);
                txtApellidoMaterno.setEnabled(true);
            }
        }
    }

    private class TXTCorreoPacienteFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            scrollPaciente.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            scrollPaciente.setVisible(false);
        }
    }

    private class TXTCorreoPacienteKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtPaciente.getText() + tecla;
            buscarPaciente(cadena);
        }
    }

    private class ListaCorreoPacienteMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String opcionBusqueda = listaPaciente.getSelectedValue().toString();
            establecerDatosPaciente(opcionBusqueda);
            txtPaciente.setText(opcionBusqueda);
            scrollPaciente.setVisible(false);
        }
    }

    private class BotonGuardarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String formaPago = comboFormaPago.getText();
            String tipoCliente = comboTipoCliente.getText();
            String correoPaciente = txtPaciente.getText();
            String nombre = txtNombre.getText();
            String apellidoPaterno = txtApellidoPaterno.getText();
            String apellidoMaterno = txtApellidoMaterno.getText();
            String municipio = txtMunicipio.getText();
            String colonia = txtColonia.getText();
            String calleNumero = txtCalleNumero.getText();

            if (datosCompletos(formaPago, tipoCliente, correoPaciente, nombre, apellidoPaterno, apellidoMaterno, municipio, colonia, calleNumero)) {
                if (!editando) {
                    System.err.println("fasdfaf");
                    InsertarSQL.insertarVenta(idUsuario, formaPago, tipoCliente, correoPaciente, nombre, apellidoPaterno, apellidoMaterno, municipio, colonia, calleNumero);

                    txtNombre.setText("Nombre");
                    txtApellidoPaterno.setText("Apellido paterno");
                    txtApellidoMaterno.setText("Apellido materno");
                    comboFormaPago.setText("Forma de pago");
                    comboTipoCliente.setText("Tipo cliente");
                    txtPaciente.setText("Paciente");
                    txtMunicipio.setText("Municipio");
                    txtColonia.setText("Colonia");
                    txtCalleNumero.setText("Calle/Numero");
                }
            } else {
                System.err.println("error");
            }
        }
    }

    private class BotonNuevoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editando = false;
            opcionBusqueda = null;
            txtFolio.setText("Folio");
            comboFormaPago.setText("Forma de pago");
            comboTipoCliente.setText("Tipo cliente");
            txtPaciente.setText("Paciente");
            txtNombre.setText("Nombre");
            txtApellidoPaterno.setText("Apellido paterno");
            txtApellidoMaterno.setText("Apellido materno");
            txtMunicipio.setText("Municipio");
            txtColonia.setText("Colonia");
            txtCalleNumero.setText("Calle/Número");
            checkActivo.setSelected(false);

            txtFolio.setEnabled(true);
            comboFormaPago.setEnabled(true);
            comboTipoCliente.setEnabled(true);
            txtPaciente.setEnabled(false);
            txtNombre.setEnabled(false);
            txtApellidoPaterno.setEnabled(false);
            txtApellidoMaterno.setEnabled(false);
            txtMunicipio.setEnabled(true);
            txtColonia.setEnabled(true);
            txtCalleNumero.setEnabled(true);
            checkActivo.setEnabled(true);
        }
    }

    private class BotonEditarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editando = true;
            if (opcionBusqueda != null) {
                txtFolio.setEnabled(false);
                comboFormaPago.setEnabled(true);
                comboTipoCliente.setEnabled(true);
                txtMunicipio.setEnabled(true);
                txtColonia.setEnabled(true);
                txtCalleNumero.setEnabled(true);
                checkActivo.setEnabled(true);
            } else {
                errorBusqueda.setVisible(true);
            }
        }
    }

    /**
     *
     * Métodos
     */


    private  void buscarVenta (String cadena) {
        //dlmBuscador.removeAllElements();
        //listaBuscador.setVisible(true);
        String sql = "SELECT folio, activo FROM Venta WHERE folio LIKE ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, cadena + "%");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                resultados++;
                String correo = rs.getObject("folio").toString();
                boolean activo = rs.getBoolean("activo");
                if(activo) {
                    //dlmBuscador.addElement(correo);
                }
            }
        } catch (SQLException sqlException) {

        }
    }

    private  void buscarPaciente (String cadena) {
        dlmPaciente.removeAllElements();
        listaPaciente.setVisible(true);
        String sql = "SELECT correo, activo FROM Paciente WHERE correo LIKE ?";
        int resultados = 0;
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, cadena + "%");
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                resultados++;
                String correo = rs.getObject("correo").toString();
                boolean activo = rs.getBoolean("activo");
                if(activo) {
                    dlmPaciente.addElement(correo);
                }
            }
        } catch (SQLException sqlException) {

        }
    }

    private  void establecerDatos (String opcionBusqueda) {
        String sql = "SELECT * FROM Venta WHERE folio = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                this.id = Integer.parseInt(rs.getObject("id").toString());
                String folio = rs.getObject("folio").toString();
                String formaPago = rs.getObject("forma_pago").toString();
                String fechaAlta = rs.getObject("fecha_alta").toString();
                String tipoCliente = rs.getObject("tipo_cliente").toString();
                String correoPaciente = rs.getObject("correo_paciente").toString();
                String nombre = rs.getObject("nombre").toString();
                String apellidoPaterno = rs.getObject("apellido_paterno").toString();
                String apellidoMaterno = rs.getObject("apellido_materno").toString();
                String municipio = rs.getObject("municipio").toString();
                String colonia = rs.getObject("colonia").toString();
                String calleNumero = rs.getObject("calle_numero").toString();
                boolean activo = rs.getBoolean("activo");

                txtFolio.setText(folio);
                comboFormaPago.setText(formaPago);
                comboTipoCliente.setText(tipoCliente);
                txtPaciente.setText(correoPaciente);
                txtNombre.setText(nombre);
                txtApellidoPaterno.setText(apellidoPaterno);
                txtApellidoMaterno.setText(apellidoMaterno);
                txtMunicipio.setText(municipio);
                txtColonia.setText(colonia);
                txtCalleNumero.setText(calleNumero);
                checkActivo.setSelected(activo);

                txtFolio.setEnabled(false);
                comboFormaPago.setEnabled(false);
                comboTipoCliente.setEnabled(false);
                txtPaciente.setEnabled(false);
                txtNombre.setEnabled(false);
                txtApellidoPaterno.setEnabled(false);
                txtApellidoMaterno.setEnabled(false);
                txtMunicipio.setEnabled(false);
                txtColonia.setEnabled(false);
                txtCalleNumero.setEnabled(false);
                checkActivo.setEnabled(false);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    private  void establecerDatosPaciente (String opcionBusqueda) {
        String sql = "SELECT nombre, apellido_paterno, apellido_materno FROM Paciente WHERE correo = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String nombre = rs.getObject("nombre").toString();
                String apellidoPaterno = rs.getObject("apellido_paterno").toString();
                String apellidoMaterno = rs.getObject("apellido_materno").toString();
                txtNombre.setText(nombre);
                txtApellidoPaterno.setText(apellidoPaterno);
                txtApellidoMaterno.setText(apellidoMaterno);
            }
        } catch (SQLException sqlException) {

        }
    }

    private  boolean datosClienteCompletos(String tipoCliente, String correo, String nombre, String apellidoPaterno, String apellidoMaterno) {
        if (!tipoCliente.equals("Paciente")) {
            if (correo.equals("Paciente")) {
                return true;
            } else {
                //no has ingresado correo
                return false;
            }
        } else {
            if (!nombre.equals("Nombre")) {
                if (!apellidoPaterno.equals("Apellido paterno")) {
                    if (!apellidoMaterno.equals("Apellido materno")) {
                        return true;
                    } else {
                        //no has ingresado apellido materno
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                //no ingreso nombre
                return false;
            }
        }
    }

    private  boolean datosCompletos(String formaPago, String tipoCliente, String correo, String nombre, String apellidoPaterno, String apellidoMaterno, String municipio, String colonia, String calleNumero) {
            if (!formaPago.equals("Forma de pago")) {
                if (!tipoCliente.equals("Tipo cliente")) {
                    if (datosClienteCompletos(tipoCliente, correo, nombre, apellidoPaterno, apellidoMaterno)) {
                        if (!municipio.equals("Municipio")) {
                            if (!colonia.equals("Colonia")) {
                                if (!calleNumero.equals("Calle/Numero")) {
                                    System.out.println("completo");
                                    return true;
                                } else {
                                    errorDatosLocalizacion.setVisible(true);
                                    return false;
                                }
                            } else {
                                errorDatosLocalizacion.setVisible(true);
                                return false;
                            }
                        } else {
                            errorDatosLocalizacion.setVisible(true);
                            return false;
                        }
                    } else {
                        errorDatosUsuario.setVisible(true);
                        return false;
                    }
                } else {
                    errorTipoPaciente.setVisible(true);
                    return false;
                }
            } else {
                errorTipoPago.setVisible(true);
                return false;
            }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        //f.add(listaBuscador);
        f.add(scrollTipoCliente);
        f.add(scrollPaciente);
        f.add(scrollFormaPago);
        f.add(errorFolio);
        f.add(errorTipoPago);
        f.add(errorTipoPaciente);
        f.add(errorDatosUsuario);
        f.add(errorDatosLocalizacion);
        f.add(errorBusqueda);
        f.add(new NuevoVenta(2));
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

}
