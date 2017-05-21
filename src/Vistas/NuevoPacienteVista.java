package Vistas;

import ComponentesBeauty.*;
import Conexion.ActualizarSQL;
import Conexion.ConexionSQL;
import Conexion.InsertarSQL;
import Utilidades.Utilidades;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utilidades.Utilidades.establecerIcono;
import static Utilidades.Utilidades.fondo;

/**
 * Created by esva on 21/05/17.
 */
public class NuevoPacienteVista extends JPanel {
    //Componentes
    BeautyImageButton btnImagenPerfil;
    BeautyImageButton btnNuevo;
    BeautyImageButton btnEditar;
    BeautyImageButton btnAntecedentes;
    BeautyImageButton btnRecomendaciones;
    BeautyTextField txtBuscar;
    BeautyTextField txtNombre;
    BeautyTextField txtApellidoPaterno;
    BeautyTextField txtApellidoMaterno;
    BeautyDateChooser txtFechaNacimiento;
    BeautyTextField txtReligion;
    BeautyTextField txtCorreo;
    BeautyTextField txtContraseña;
    BeautyTextField txtTelefono;
    BeautyTextField txtTelefonoEmergencias;
    BeautyCheckbox checkActivo;
    BeautyBlackButton btnGuardar;

    //Lista para buscador
    //public static DefaultListModel dlmBuscador = new DefaultListModel();
    //public static BeautyList listaBuscador = new BeautyList();

    //Mensajes de error
    public static BeautyErrorMessage errorNombre = new BeautyErrorMessage("Ingresa un nombre...");
    public static BeautyErrorMessage errorApellidoPaterno = new BeautyErrorMessage("Ingresa el apellido paterno...");
    public static BeautyErrorMessage errorApellidoMaterno = new BeautyErrorMessage("Ingresa el apellido materno...");
    public static BeautyErrorMessage errorFechaNacimiento = new BeautyErrorMessage("Ingresa la fecha de nacimiento...");
    public static BeautyErrorMessage errorReligion = new BeautyErrorMessage("Ingresa una religión...");
    public static BeautyErrorMessage errorCorreo = new BeautyErrorMessage("El usuario debe tener un correo...");
    public static BeautyErrorMessage errorNoEsCorreo = new BeautyErrorMessage("Ingresa un correo valido...");
    public static BeautyErrorMessage errorFormato = new BeautyErrorMessage("Ingresa un carácter numérico...");
    public static BeautyErrorMessage errorEditar = new BeautyErrorMessage("No has seleccionado un registro para editar...");
    public static BeautyErrorMessage errorCorreoRepetido = new BeautyErrorMessage("Ya existe un paciente con ese correo");
    public static BeautyErrorMessage pantallaOK = new BeautyErrorMessage("Paciente registrado exitosamente");

    //Variables
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String opcionBusqueda = null;
    String pathFoto = null;
    boolean editando = false;
    int id = 0;
    int idUsuario = 0;

    //Variables para buscar un archivo
    JFileChooser jFile = new JFileChooser();
    BufferedImage image = null;
    File file;
    FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());

    public NuevoPacienteVista(int idUsuario) {
        this.idUsuario = idUsuario;
        componentes();
        this.setLayout(null);
        this.setBounds(0, 0, 750, 625);
        this.setVisible(true);
    }

    private void componentes() {
        btnImagenPerfil = new BeautyImageButton(establecerIcono("FotoPerfil", 200, 200), 290, 10, 200, 200);
        btnNuevo = new BeautyImageButton(establecerIcono("Nuevo", 20, 20), 620, 130, 20, 20);
        btnEditar = new BeautyImageButton(establecerIcono("Editar", 20, 20), 660, 130, 20, 20);
        btnAntecedentes = new BeautyImageButton(establecerIcono("Antecedentes", 20, 20), 700, 130, 20, 20);
        btnRecomendaciones = new BeautyImageButton(establecerIcono("Recomendacion", 20, 20), 740, 130, 20, 20);
        txtBuscar = new BeautyTextField("Buscar...", 300, 120, 300, 30);

        txtNombre = new BeautyTextField("Nombre", 20 ,280, 200, 20);
        txtApellidoPaterno = new BeautyTextField("Apellido paterno", 270, 280, 200, 20);
        txtApellidoMaterno = new BeautyTextField("Apellido materno", 530, 280, 200, 20);
        txtFechaNacimiento = new BeautyDateChooser(20, 370, 200, 20);
        txtReligion = new BeautyTextField("Religión", 270, 370, 200, 20);
        txtCorreo = new BeautyTextField("Correo", 20, 470, 200, 20);
        txtTelefono =  new BeautyTextField("Teléfono", 270, 470, 200, 20);
        txtTelefonoEmergencias = new BeautyTextField("Teléfono emergencias", 530, 470, 200, 20);

        checkActivo = new BeautyCheckbox("Activo", 60, 550, 200);
        btnGuardar = new BeautyBlackButton("Guardar", 620, 550, 100, 30);

        btnImagenPerfil.addActionListener(new CambiarImagen());
        txtBuscar.addKeyListener(new BuscarKeyAdapter());
        txtBuscar.addFocusListener(new BuscarFocusAdapter());
        //listaBuscador.addMouseListener(new ListaBuscarMouseAdapter());
        btnNuevo.addActionListener(new BotonNuevo());
        btnEditar.addActionListener(new BotonEditar());
        btnGuardar.addActionListener(new BotonGuardar());

        this.add(btnImagenPerfil);
        //this.add(btnNuevo);
        //this.add(btnEditar);
        //this.add(btnAntecedentes);
        //this.add(btnRecomendaciones);
        //this.add(txtBuscar);
        this.add(txtNombre);
        this.add(txtApellidoPaterno);
        this.add(txtApellidoMaterno);
        this.add(txtFechaNacimiento);
        this.add(txtReligion);
        this.add(txtCorreo);
        this.add(txtTelefono);
        this.add(txtTelefonoEmergencias);
        this.add(checkActivo);
        this.add(btnGuardar);
    }

    /**
     * Clases privadas para acción de componentes
     */

    private class CambiarImagen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jFile.setFileFilter(imageFilter);
            jFile.showOpenDialog(null);
            file = jFile.getSelectedFile();
            try {
                image = ImageIO.read(file);
                ImageIcon icono = new ImageIcon(image);
                Icon i = new ImageIcon(icono.getImage().getScaledInstance(btnImagenPerfil.getWidth(), btnImagenPerfil.getHeight(), Image.SCALE_DEFAULT));
                btnImagenPerfil.setIcon(i);
            } catch (IOException ioException) {

            } catch (IllegalArgumentException argumentException) {

            }
        }
    }

    private class BotonGuardar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = txtNombre.getText();
            String apellidoPaterno = txtApellidoPaterno.getText();
            String apellidoMaterno = txtApellidoMaterno.getText();
            String fechaNacimiento = txtFechaNacimiento.getDate().getYear() + "-" + txtFechaNacimiento.getDate().getMonth() + "-" + txtFechaNacimiento.getDate().getDay();
            String religion = txtReligion.getText();
            String correo = txtCorreo.getText();
            String telefono  = txtTelefono.getText();
            String telefonoEmergencias = txtTelefonoEmergencias.getText();
            boolean activo = checkActivo.isSelected();

            //si escogio imagen le asigna la ruta, si no se queda por default
            if(file != null) {
                pathFoto = file.getPath();
            } else {
                pathFoto = "src/Iconos/icn_FotoPerfil.png";
            }

            if (datosCompletos(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, religion, correo, telefono, telefonoEmergencias)) {
                if(!editando) {
                    InsertarSQL.insertarPaciente(idUsuario, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, religion, correo, telefono, telefonoEmergencias, activo, pathFoto);
                    txtNombre.setHint("Nombre");
                    txtApellidoPaterno.setHint("Apellido paterno");
                    txtApellidoMaterno.setHint("Apellido materno");
                    //txtFechaNacimiento.setHint("Fecha nacimiento");
                    txtReligion.setHint("Religión");
                    txtCorreo.setHint("Correo");
                    txtTelefono.setHint("Teléfono");
                    txtTelefonoEmergencias.setHint("Teléfono emergencias");
                    checkActivo.setSelected(false);
                    txtFechaNacimiento.setDate(null);
                    btnImagenPerfil.setIcon(establecerIcono("FotoPerfil", 150, 150));
                    pantallaOK.setVisible(true);
                } else {
                    ActualizarSQL.actualizarPaciente(correo, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, religion, telefono, telefonoEmergencias, activo, pathFoto);
                    InsertarSQL.insertarModificacion(id, idUsuario, "Paciente");
                    txtNombre.setHint("Nombre");
                    txtApellidoPaterno.setHint("Apellido paterno");
                    txtApellidoMaterno.setHint("Apellido materno");
                    //txtFechaNacimiento.setHint("Fecha nacimiento");
                    txtReligion.setHint("Religión");
                    txtCorreo.setHint("Correo");
                    txtTelefono.setHint("Teléfono");
                    txtTelefonoEmergencias.setHint("Teléfono emergencias");
                    checkActivo.setSelected(false);
                    btnImagenPerfil.setIcon(establecerIcono("FotoPerfil", 150, 150));
                    txtCorreo.setEnabled(true);
                }
            }
            opcionBusqueda = null;
            editando = false;
        }
    }

    private class BotonEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(opcionBusqueda != null) {
                editando = true;
                btnGuardar.setEnabled(true);
                btnImagenPerfil.setEnabled(true);
                txtNombre.setEnabled(true);
                txtApellidoPaterno.setEnabled(true);
                txtApellidoMaterno.setEnabled(true);
                txtFechaNacimiento.setEnabled(true);
                txtReligion.setEnabled(true);
                txtCorreo.setEnabled(false);
                txtTelefono.setEnabled(true);
                txtTelefonoEmergencias.setEnabled(true);
                checkActivo.setEnabled(true);
            } else {
                errorEditar.setVisible(true);
            }
        }
    }

    private class BotonNuevo implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e ) {
            editando = false;
            opcionBusqueda = null;
            btnGuardar.setEnabled(true);
            btnImagenPerfil.setIcon(establecerIcono("FotoPerfil", 150, 150));
            txtBuscar.setText("Buscar...");
            txtNombre.setText("Nombre");
            txtApellidoPaterno.setText("Apellido paterno");
            txtApellidoMaterno.setText("Apellido materno");
           //txtFechaNacimiento.setText("Fecha nacimiento");
            txtReligion.setText("Religión");
            txtCorreo.setText("Correo");
            txtTelefono.setText("Teléfono");
            txtTelefonoEmergencias.setText("Teléfono emergencias");
            checkActivo.setSelected(false);

            btnImagenPerfil.setEnabled(true);
            txtNombre.setEnabled(true);
            txtApellidoPaterno.setEnabled(true);
            txtApellidoMaterno.setEnabled(true);
            txtFechaNacimiento.setEnabled(false);
            txtReligion.setEnabled(true);
            txtCorreo.setEnabled(true);
            txtTelefono.setEnabled(true);
            txtTelefonoEmergencias.setEnabled(true);
            checkActivo.setEnabled(true);
        }
    }

    private class BuscarKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char tecla = e.getKeyChar();
            String cadena = txtBuscar.getText() + tecla;
            buscarPaciente(cadena);
        }
    }

    private class BuscarFocusAdapter extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            //listaBuscador.setVisible(true);
        }

        @Override
        public void focusLost(FocusEvent e) {
            //listaBuscador.setVisible(false);
        }
    }

    private class ListaBuscarMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                //opcionBusqueda = listaBuscador.getSelectedValue().toString();
                establecerDatos(opcionBusqueda);
                //listaBuscador.setVisible(false);
                txtBuscar.setText("");
                btnGuardar.setEnabled(false);
            } catch (NullPointerException nullException) {

            }
        }
    }


    /**
     *
     * Metodos
     */
    private  boolean esCorreo (String correo) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    private  boolean datosPersonalesCompletos (String nombre, String apellidoPaterno, String apellidoMaterno,String fechaNacimiento, String religion) {
        if (!nombre.equals("Nombre")) {
            if (!apellidoPaterno.equals("Apellido paterno")) {
                if (!apellidoMaterno.equals("Apellido materno")) {
                    if(!fechaNacimiento.equals(null)) {
                        if(!religion.equals("Religión")) {
                            return true;
                        } else {
                            errorReligion.setVisible(true);
                            return false;
                        }
                    } else {
                        errorFechaNacimiento.setVisible(true);
                        return false;
                    }
                } else {
                    errorApellidoMaterno.setVisible(true);
                    return false;
                }
            } else {
                errorApellidoPaterno.setVisible(true);
                return false;
            }
        } else {
            errorNombre.setVisible(true);
            return false;
        }
    }

    private  boolean datosContactoCompletos (String correo, String telefono, String telefonoEmergencias) {
        if (!correo.equals("Correo")) {
            if (esCorreo(correo)) {
                if (!telefono.equals("Teléfono")) {
                    if (!telefonoEmergencias.equals("Télefono emergencias")) {
                        return true;
                    } else {
                        errorFormato.setVisible(true);
                        return false;
                    }
                } else {
                    errorFormato.setVisible(true);
                    return false;
                }
            } else {
                errorNoEsCorreo.setVisible(true);
                return false;
            }
        } else {
            errorCorreo.setVisible(true);
            return false;
        }
    }

    private  boolean correoRepetido (String correo) {
        String sql = "SELECT * FROM Paciente WHERE correo = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                errorCorreoRepetido.setVisible(true);
                return false;
            } else {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.getMessage();
            return false;
        }
    }

    private  boolean datosCompletos (String nombre, String apellidoPaterno, String apellidoMaterno,String fechaNacimiento, String religion, String correo, String telefono, String telefonoEmergencias) {
        if (datosPersonalesCompletos(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, religion)) {
            if (datosContactoCompletos(correo, telefono, telefonoEmergencias)) {
                if (correoRepetido(correo)) {
                    return true;
                }
            }
        }
        return false;
    }

    private  void buscarPaciente (String cadena) {
        //dlmBuscador.removeAllElements();
        //listaBuscador.setVisible(true);
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
          //          dlmBuscador.addElement(correo);
                }
            }
        } catch (SQLException sqlException) {

        }
    }

    private  void establecerDatos (String opcionBusqueda) {
        String sql = "SELECT * FROM Paciente WHERE correo = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, opcionBusqueda);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                this.id = Integer.parseInt(rs.getObject("id").toString());
                String pathFoto = rs.getObject("path_foto").toString();
                String nombre = rs.getObject("nombre").toString();
                String apellidoPaterno = rs.getObject("apellido_paterno").toString();
                String apellidoMaterno = rs.getObject("apellido_materno").toString();
                String fechaNacimiento = rs.getObject("fecha_nacimiento").toString();
                String religion = rs.getObject("religion").toString();
                String correo = rs.getObject("correo").toString();
                String telefono = rs.getObject("telefono").toString();
                String telefonoEmergencias = rs.getObject("telefono_emergencias").toString();
                boolean activo = rs.getBoolean("activo");

                btnImagenPerfil.setIcon(Utilidades.establecerImagenPath(pathFoto, 200, 200));
                txtNombre.setText(nombre);
                txtApellidoPaterno.setText(apellidoPaterno);
                txtApellidoMaterno.setText(apellidoMaterno);
                //txtFechaNacimiento.setText(fechaNacimiento);
                txtReligion.setText(religion);
                txtCorreo.setText(correo);
                txtTelefono.setText(telefono);
                txtTelefonoEmergencias.setText(telefonoEmergencias);
                checkActivo.setSelected(activo);

                btnImagenPerfil.setEnabled(false);
                txtNombre.setEnabled(false);
                txtApellidoPaterno.setEnabled(false);
                txtApellidoMaterno.setEnabled(false);
                txtFechaNacimiento.setEnabled(false);
                txtReligion.setEnabled(false);
                txtCorreo.setEnabled(false);
                txtTelefono.setEnabled(false);
                txtTelefonoEmergencias.setEnabled(false);
                checkActivo.setEnabled(false);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    /*
    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        //f.add(listaBuscador);
        f.add(errorNombre);
        f.add(new NuevoPacienteVista());
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

    }*/

}
