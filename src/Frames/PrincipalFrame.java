package Frames;

import ComponentesBeauty.BeautyImageButton;
import Conexion.ConexionSQL;
import Utilidades.Utilidades;
import Vistas.*;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by esva on 18/05/17.
 */
public class PrincipalFrame extends JFrame {
    //Paneles principales
    private JPanel panelBarraSuperior;
    private JPanel panelMenu;
    private JPanel panelCentral;

    //Botones barra superior
    private BeautyImageButton botonCerrar;
    private BeautyImageButton botonMinimizar;

    //Paneles para menu
    private JPanel panelInfoUsuario;
    private JPanel panelLista;

    //Componentes para el panel info usuario
    private JLabel botonImagenUsuario;
    private JLabel etiquetaNombreUsuario;
    private JLabel etiquetaCorreoUsuario;

    //Componentes para el panel lista
    //Sección home
    private BeautyImageButton botonHome;
    //Sección usuario
    private JLabel separacionUsuario;
    private JLabel etiquetaUsuario;
    private BeautyImageButton botonUsuarioNuevo;
    private BeautyImageButton botonUsuarioVer;
    private BeautyImageButton botonUsuarioEditar;
    //Sección producto
    private JLabel separacionProducto;
    private JLabel etiquetaProducto;
    private BeautyImageButton botonProductoNuevo;
    private BeautyImageButton botonProductoVer;
    private BeautyImageButton botonProductoEditar;
    //Sección paciente
    private JLabel separacionPaciente;
    private JLabel etiquetaPaciente;
    private BeautyImageButton botonPacienteNuevo;
    private BeautyImageButton botonPacienteVer;
    private BeautyImageButton botonPacienteEditar;
    private BeautyImageButton botonPacienteAntecedente;
    private BeautyImageButton botonPacienteRecomendacion;
    //Sección venta
    private JLabel separacionVenta;
    private JLabel etiquetaVenta;
    private BeautyImageButton botonVentaNuevo;
    private BeautyImageButton botonVentaProducto;
    private BeautyImageButton botonVentaServicio;
    private BeautyImageButton botonVentaImprimir;
    //Sección otros
    private JLabel separacionOtros;
    private JLabel etiquetaOtros;
    private BeautyImageButton botonLlegadaMercancia;
    private BeautyImageButton botonBaseDeDatos;
    //Sección cerrarSesion
    private JLabel separacionCerrarSesion;
    private BeautyImageButton botonCerrarSesion;

    //Variables de sesión
    int id = 0;
    String correo = null;
    String nombre = null;
    String pathFoto = null;
    boolean administrador = false;

    //PANELES DE PRUEBA!!!!!!!
    JPanel panel1;
    JPanel panel2;
    JTextField txt1;
    JTextField txt2;

    //Paneles contenido
    private HomeVista homeVista;
    private NuevoUsuarioVista nuevoUsuarioVista;
    private VerUsuarioVista verUsuarioVista;
    private EditarUsuarioVista editarUsuarioVista;
    private NuevoProductoVista nuevoProductoVista;
    private VerProductoVista verProductoVista;
    private EditarProductoVista editarProductoVista;
    private NuevoPacienteVista nuevoPacienteVista;
    private VerPacienteVista verPacienteVista;
    private EditarPacienteVista editarPacienteVista;
    private NuevoAntecedenteVista nuevoAntecedenteVista;
    private NuevoRecomendacionVista nuevoRecomendacionVista;
    private NuevoVenta nuevoVenta;
    private NuevoVentaProducto nuevoVentaProducto;
    private NuevoVentaServicio nuevoVentaServicio;
    private ImprimirVista imprimirVista;
    private LlegadaMercanciaVista llegadaMercanciaVista;
    private BaseDeDatosVista baseDeDatosVista;

    public PrincipalFrame(String correo) {
        this.correo = correo;
        establecerDatosUsuario(this.correo);

        homeVista = new HomeVista();
        nuevoUsuarioVista = new NuevoUsuarioVista(this.id);
        verUsuarioVista = new VerUsuarioVista();
        editarUsuarioVista = new EditarUsuarioVista(this.id);
        nuevoProductoVista = new NuevoProductoVista(this.id);
        verProductoVista = new VerProductoVista();
        editarProductoVista = new EditarProductoVista(this.id);
        nuevoPacienteVista = new NuevoPacienteVista(this.id);
        verPacienteVista = new VerPacienteVista();
        editarPacienteVista = new EditarPacienteVista(this.id);
        nuevoAntecedenteVista = new NuevoAntecedenteVista(this.id);
        nuevoRecomendacionVista = new NuevoRecomendacionVista();
        nuevoVenta = new NuevoVenta(this.id);
        nuevoVentaProducto = new NuevoVentaProducto();
        nuevoVentaServicio = new NuevoVentaServicio();
        llegadaMercanciaVista = new LlegadaMercanciaVista();
        baseDeDatosVista = new BaseDeDatosVista();
        imprimirVista = new ImprimirVista();

        mensajesError();
        listas();
        componentes();
        propiedades();
    }

    private void propiedades() {
        this.setSize(900, 655);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setTitle("Clinica Control");
        this.setIconImage(new ImageIcon("src/Iconos/icn_logo.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void mensajesError() {
        //Vista nuevo usuario
        this.add(nuevoUsuarioVista.errorRol);
        this.add(nuevoUsuarioVista.errorNombre);
        this.add(nuevoUsuarioVista.errorApellidoPaterno);
        this.add(nuevoUsuarioVista.errorNoEsCorreo);
        this.add(nuevoUsuarioVista.errorCorreo);
        this.add(nuevoUsuarioVista.errorCorreoRepetido);
        this.add(nuevoUsuarioVista.errorContraseña1);
        this.add(nuevoUsuarioVista.errorNoIguales);
        this.add(nuevoUsuarioVista.pantallaOK);
        //Vista editar usuario
        this.add(editarUsuarioVista.errorRol);
        this.add(editarUsuarioVista.errorNombre);
        this.add(editarUsuarioVista.errorApellidoPaterno);
        this.add(editarUsuarioVista.errorApellidoMaterno);
        this.add(editarUsuarioVista.errorCorreo);
        this.add(editarUsuarioVista.errorContraseña1);
        this.add(editarUsuarioVista.errorContraseña2);
        this.add(editarUsuarioVista.errorNoIguales);
        this.add(editarUsuarioVista.pantallaOK);
        //Vista nuevo producto
        this.add(nuevoProductoVista.errorFormato);
        this.add(nuevoProductoVista.errorNombre);
        this.add(nuevoProductoVista.errorProveedor);
        this.add(nuevoProductoVista.errorTipo);
        this.add(nuevoProductoVista.pantallaOK);
        //Vista editar producto
        this.add(editarProductoVista.errorFormato);
        this.add(editarProductoVista.errorNombre);
        this.add(editarProductoVista.errorProveedor);
        this.add(editarProductoVista.errorTipo);
        this.add(editarProductoVista.pantallaOK);
        //Vista nuevo paciente
        this.add(nuevoPacienteVista.errorNombre);
        this.add(nuevoPacienteVista.errorApellidoPaterno);
        this.add(nuevoPacienteVista.errorApellidoMaterno);
        this.add(nuevoPacienteVista.errorFechaNacimiento);
        this.add(nuevoPacienteVista.errorReligion);
        this.add(nuevoPacienteVista.errorCorreo);
        this.add(nuevoPacienteVista.errorNoEsCorreo);
        this.add(nuevoPacienteVista.errorCorreoRepetido);
        this.add(nuevoPacienteVista.errorFormato);
        this.add(nuevoPacienteVista.pantallaOK);
        //Vista editar paciente
        this.add(editarPacienteVista.errorNombre);
        this.add(editarPacienteVista.errorApellidoPaterno);
        this.add(editarPacienteVista.errorApellidoMaterno);
        this.add(editarPacienteVista.errorFechaNacimiento);
        this.add(editarPacienteVista.errorReligion);
        this.add(editarPacienteVista.errorCorreo);
        this.add(editarPacienteVista.errorNoEsCorreo);
        this.add(editarPacienteVista.errorCorreoRepetido);
        this.add(editarPacienteVista.errorFormato);
        this.add(editarPacienteVista.pantallaOK);
        //Vista antecedente
        this.add(nuevoAntecedenteVista.errorTipo);
        this.add(nuevoAntecedenteVista.errorEnfermedad1);
        this.add(nuevoAntecedenteVista.errorEnfermedad2);
        this.add(nuevoAntecedenteVista.errorAño);
        this.add(nuevoAntecedenteVista.errorDescripcion);
        this.add(nuevoAntecedenteVista.errorTratamiento);
        //Vista recomendacion
        this.add(nuevoRecomendacionVista.errorMedico);
        this.add(nuevoRecomendacionVista.errorRecomendacion);
        //Vista nueva venta
        this.add(nuevoVenta.errorDatosLocalizacion);
        this.add(nuevoVenta.errorTipoPago);
        this.add(nuevoVenta.errorTipoPaciente);
        this.add(nuevoVenta.errorDatosUsuario);
        //Vista nueva venta producto
        this.add(nuevoVentaProducto.errorProducto);
        this.add(nuevoVentaProducto.errorPresentacion);
        //Vista imprimir
        this.add(imprimirVista.pantallaOK);
        //Llegada de mercancia
        this.add(llegadaMercanciaVista.errorFormato);
        this.add(llegadaMercanciaVista.errorOpcion);
    }

    private void listas() {
        this.add(nuevoUsuarioVista.scrollRol);
        this.add(verUsuarioVista.scrollBuscador);
        this.add(editarUsuarioVista.scrollRol);
        this.add(editarUsuarioVista.scrollBuscador);
        this.add(nuevoProductoVista.scrollPresentacion);
        this.add(verProductoVista.scrollBuscador);
        this.add(editarProductoVista.scrollPresentacion);
        this.add(editarProductoVista.scrollBuscador);
        this.add(verPacienteVista.scrollBuscador);
        this.add(editarPacienteVista.scrollBuscador);
        this.add(nuevoAntecedenteVista.scrollBuscador);
        this.add(nuevoAntecedenteVista.scrollTipo);
        this.add(nuevoAntecedenteVista.scrollEnfermedad);
        this.add(nuevoRecomendacionVista.scrollBuscador);
        this.add(nuevoRecomendacionVista.scrollDoctor);
        this.add(nuevoVenta.scrollFormaPago);
        this.add(nuevoVenta.scrollTipoCliente);
        this.add(nuevoVenta.scrollPaciente);
        this.add(nuevoVentaProducto.scrollBuscador);
        this.add(nuevoVentaProducto.scrollProducto);
        this.add(nuevoVentaProducto.scrollPresentacion);
        this.add(nuevoVentaServicio.scrollBuscador);
        this.add(nuevoVentaServicio.scrollMedico);
        this.add(imprimirVista.scrollBuscador);
        this.add(llegadaMercanciaVista.scrollBuscador);
        this.add(baseDeDatosVista.scrollTablas);
    }

    private void componentes() {
        //Los 3 paneles principales
        panelBarraSuperior = new JPanel();
        panelMenu = new JPanel();
        panelCentral = new JPanel();

        //Panel barra superior
        botonCerrar = new BeautyImageButton(Utilidades.establecerIcono("Null", 12, 12), 10, 9, 12, 12);
        botonMinimizar = new BeautyImageButton(Utilidades.establecerIcono("Null", 12, 12), 35, 9, 12, 12);

        botonCerrar.setRolloverIcon(Utilidades.establecerIcono("Cerrar", 12, 12));
        botonMinimizar.setRolloverIcon(Utilidades.establecerIcono("Minimizar", 12, 12));

        botonMinimizar.addActionListener((ActionEvent) -> {
            this.setExtendedState(JFrame.HIDE_ON_CLOSE);
        });

        botonCerrar.addActionListener((ActionEvent) -> {
            System.exit(JFrame.NORMAL);
        });

        panelBarraSuperior.setLayout(null);
        panelBarraSuperior.setBackground(new Color(255, 255, 255, 255));
        panelBarraSuperior.setSize(900, 30);

        panelBarraSuperior.add(botonCerrar);
        panelBarraSuperior.add(botonMinimizar);

        //PanelMenu
        panelInfoUsuario = new JPanel();
        panelLista = new JPanel();
        botonImagenUsuario = new JLabel();
        botonImagenUsuario.setIcon(Utilidades.establecerImagenPath(pathFoto, 70, 70));
        botonImagenUsuario.setBounds(0, 10, 150, 70);
        botonImagenUsuario.setHorizontalAlignment(JLabel.CENTER);

        etiquetaNombreUsuario = new JLabel("Nombre usuario");
        etiquetaNombreUsuario.setText(nombre);
        etiquetaNombreUsuario.setBounds(0, 90, 150, 20);
        etiquetaNombreUsuario.setHorizontalAlignment(JLabel.CENTER);
        etiquetaNombreUsuario.setFont(Utilidades.quickSandFont());
        etiquetaNombreUsuario.setForeground(Color.WHITE);

        etiquetaCorreoUsuario = new JLabel("Correo usuario");
        etiquetaCorreoUsuario.setText(correo);
        etiquetaCorreoUsuario.setBounds(0, 120, 150, 20);
        etiquetaCorreoUsuario.setHorizontalAlignment(JLabel.CENTER);
        etiquetaCorreoUsuario.setFont(Utilidades.quickSandFont());
        etiquetaCorreoUsuario.setForeground(Color.WHITE);

        panelInfoUsuario.setLayout(null);
        panelInfoUsuario.setBounds(0, 0, 150, 160);
        panelInfoUsuario.setBackground(new Color(73, 201, 180, 255));
        panelInfoUsuario.add(botonImagenUsuario);
        panelInfoUsuario.add(etiquetaNombreUsuario);
        panelInfoUsuario.add(etiquetaCorreoUsuario);

        //Menu
        botonHome = new BeautyImageButton(Utilidades.establecerIcono("Home", 10, 10), 10, 10, 150, 10, "Home");
        botonHome.setHorizontalAlignment(JButton.LEFT);

        separacionUsuario = new JLabel();
        separacionUsuario.setBackground(new Color(63, 65, 80));
        separacionUsuario.setOpaque(true);
        separacionUsuario.setBounds(0, 27, 150, 2);

        etiquetaUsuario = new JLabel("Usuario");
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setFont(Utilidades.quickSandFont());
        etiquetaUsuario.setBounds(0, 28, 150, 20);
        etiquetaUsuario.setHorizontalAlignment(JLabel.LEFT);

        botonUsuarioNuevo = new BeautyImageButton(Utilidades.establecerIcono("Nuevo", 10, 10), 10, 48, 150, 10, "Nuevo");
        botonUsuarioNuevo.setHorizontalAlignment(JButton.LEFT);

        botonUsuarioVer = new BeautyImageButton(Utilidades.establecerIcono("Ver", 10, 10), 10, 68, 150, 10, "Ver");
        botonUsuarioVer.setHorizontalAlignment(JButton.LEFT);

        botonUsuarioEditar = new BeautyImageButton(Utilidades.establecerIcono("Editar", 10, 10), 10, 88, 150, 10, "Editar");
        botonUsuarioEditar.setHorizontalAlignment(JButton.LEFT);

        separacionProducto = new JLabel();
        separacionProducto.setOpaque(true);
        separacionProducto.setBackground(new Color(63, 65, 80));
        separacionProducto.setBounds(0, 100, 150, 2);

        etiquetaProducto = new JLabel("Producto");
        etiquetaProducto.setForeground(Color.WHITE);
        etiquetaProducto.setFont(Utilidades.quickSandFont());
        etiquetaProducto.setBounds(0, 103, 150, 20);
        etiquetaProducto.setHorizontalAlignment(JLabel.LEFT);

        botonProductoNuevo = new BeautyImageButton(Utilidades.establecerIcono("Nuevo", 10, 10), 10, 123, 150, 10, "Nuevo");
        botonProductoNuevo.setHorizontalAlignment(JLabel.LEFT);

        botonProductoVer = new BeautyImageButton(Utilidades.establecerIcono("Ver", 10, 10), 10, 143, 150, 10, "Ver");
        botonProductoVer.setHorizontalAlignment(JLabel.LEFT);

        botonProductoEditar = new BeautyImageButton(Utilidades.establecerIcono("Editar", 10, 10), 10, 163, 150, 10, "Editar");
        botonProductoEditar.setHorizontalAlignment(JLabel.LEFT);

        separacionPaciente = new JLabel();
        separacionPaciente.setOpaque(true);
        separacionPaciente.setBackground(new Color(63, 65, 80));
        separacionPaciente.setBounds(0, 175, 150, 2);

        etiquetaPaciente = new JLabel("Paciente");
        etiquetaPaciente.setForeground(Color.WHITE);
        etiquetaPaciente.setFont(Utilidades.quickSandFont());
        etiquetaPaciente.setBounds(0, 178, 150, 20);
        etiquetaPaciente.setHorizontalAlignment(JLabel.LEFT);

        botonPacienteNuevo = new BeautyImageButton(Utilidades.establecerIcono("Nuevo", 10, 10), 10, 198, 150, 10, "Nuevo");
        botonPacienteNuevo.setHorizontalAlignment(JButton.LEFT);

        botonPacienteVer = new BeautyImageButton(Utilidades.establecerIcono("Ver", 10, 10), 10, 218, 150, 10, "Ver");
        botonPacienteVer.setHorizontalAlignment(JButton.LEFT);

        botonPacienteEditar = new BeautyImageButton(Utilidades.establecerIcono("Editar", 10, 10), 10, 238, 150, 10, "Editar");
        botonPacienteEditar.setHorizontalAlignment(JButton.LEFT);

        botonPacienteAntecedente = new BeautyImageButton(Utilidades.establecerIcono("Antecedentes", 5, 10), 10, 258, 150, 10, "Antecedentes");
        botonPacienteAntecedente.setHorizontalAlignment(JButton.LEFT);

        botonPacienteRecomendacion = new BeautyImageButton(Utilidades.establecerIcono("Recomendacion", 10, 10), 10, 278, 150, 10, "Recomendaciones");
        botonPacienteRecomendacion.setHorizontalAlignment(JButton.LEFT);

        separacionVenta = new JLabel();
        separacionVenta.setOpaque(true);
        separacionVenta.setBackground(new Color(63, 65, 80));
        separacionVenta.setBounds(0, 290, 150, 2);

        etiquetaVenta = new JLabel("Ventas");
        etiquetaVenta.setForeground(Color.WHITE);
        etiquetaVenta.setFont(Utilidades.quickSandFont());
        etiquetaVenta.setBounds(0, 293, 150, 20);
        etiquetaVenta.setHorizontalAlignment(JLabel.LEFT);

        botonVentaNuevo = new BeautyImageButton(Utilidades.establecerIcono("Nuevo", 10, 10), 10, 313, 150, 10, "Nuevo");
        botonVentaNuevo.setHorizontalAlignment(JButton.LEFT);

        botonVentaProducto = new BeautyImageButton(Utilidades.establecerIcono("Producto", 10, 10), 10, 333, 150, 10, "Producto");
        botonVentaProducto.setHorizontalAlignment(JButton.LEFT);

        botonVentaServicio = new BeautyImageButton(Utilidades.establecerIcono("Servicio", 10, 10), 10, 353, 150, 10, "Servicio");
        botonVentaServicio.setHorizontalAlignment(JButton.LEFT);

        botonVentaImprimir = new BeautyImageButton(Utilidades.establecerIcono("Factura", 10, 10), 10, 373, 150, 10, "Imprimir");
        botonVentaImprimir.setHorizontalAlignment(JButton.LEFT);

        separacionOtros = new JLabel();
        separacionOtros.setOpaque(true);
        separacionOtros.setBackground(new Color(63, 65, 80));
        separacionOtros.setBounds(0, 385, 150, 2);

        etiquetaOtros = new JLabel("Otros");
        etiquetaOtros.setForeground(Color.WHITE);
        etiquetaOtros.setFont(Utilidades.quickSandFont());
        etiquetaOtros.setBounds(0, 388, 150, 20);
        etiquetaOtros.setHorizontalAlignment(JLabel.LEFT);

        botonLlegadaMercancia = new BeautyImageButton(Utilidades.establecerIcono("LlegadaMercancia", 10, 10), 10, 408, 150, 10, "Llegada Mercancia");
        botonLlegadaMercancia.setHorizontalAlignment(JButton.LEFT);

        botonBaseDeDatos = new BeautyImageButton(Utilidades.establecerIcono("BaseDeDatos", 10, 10), 10, 428, 150, 10, "Base de datos");
        botonBaseDeDatos.setHorizontalAlignment(JButton.LEFT);

        separacionCerrarSesion = new JLabel();
        separacionCerrarSesion.setOpaque(true);
        separacionCerrarSesion.setBackground(new Color(63, 65, 80));
        separacionCerrarSesion.setBounds(0, 440, 150, 2);

        botonCerrarSesion = new BeautyImageButton(Utilidades.establecerIcono("CerrarSesion", 10, 10), 10, 450, 150, 10, "Cerrar sesión");
        botonCerrarSesion.setHorizontalAlignment(JButton.LEFT);


        panelLista = new JPanel();
        panelLista.setLayout(null);
        panelLista.setBounds(0, 160, 150, 470);
        panelLista.setBackground(new Color(51, 53, 68));
        panelLista.add(botonHome);
        panelLista.add(separacionUsuario);
        panelLista.add(etiquetaUsuario);
        panelLista.add(botonUsuarioNuevo);
        panelLista.add(botonUsuarioVer);
        panelLista.add(botonUsuarioEditar);
        panelLista.add(separacionProducto);
        panelLista.add(etiquetaProducto);
        panelLista.add(botonProductoNuevo);
        panelLista.add(botonProductoVer);
        panelLista.add(botonProductoEditar);
        panelLista.add(separacionPaciente);
        panelLista.add(etiquetaPaciente);
        panelLista.add(botonPacienteNuevo);
        panelLista.add(botonPacienteVer);
        panelLista.add(botonPacienteEditar);
        panelLista.add(botonPacienteAntecedente);
        panelLista.add(botonPacienteRecomendacion);
        panelLista.add(separacionVenta);
        panelLista.add(etiquetaVenta);
        panelLista.add(botonVentaNuevo);
        panelLista.add(botonVentaProducto);
        panelLista.add(botonVentaServicio);
        panelLista.add(botonVentaImprimir);
        panelLista.add(separacionOtros);
        panelLista.add(etiquetaOtros);
        panelLista.add(botonLlegadaMercancia);
        panelLista.add(botonBaseDeDatos);
        panelLista.add(separacionCerrarSesion);
        panelLista.add(botonCerrarSesion);

        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 30, 150, 630);
        panelMenu.add(panelInfoUsuario);
        panelMenu.add(panelLista);



        //PanelCentral
        panelCentral.add(homeVista);
        panelCentral.setLayout(new CardLayout());
        panelCentral.setBackground(Color.BLACK);
        panelCentral.setBounds(150, 30, 750, 625);


        botonHome.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(homeVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonUsuarioNuevo.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoUsuarioVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonUsuarioVer.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(verUsuarioVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });


        botonUsuarioEditar.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(editarUsuarioVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonProductoNuevo.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoProductoVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonProductoVer.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(verProductoVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonProductoEditar.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(editarProductoVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonPacienteNuevo.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoPacienteVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonPacienteVer.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(verPacienteVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonPacienteEditar.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(editarPacienteVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonPacienteAntecedente.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoAntecedenteVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonPacienteRecomendacion.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoRecomendacionVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonVentaNuevo.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoVenta);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonVentaProducto.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoVentaProducto);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonVentaServicio.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(nuevoVentaServicio);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonVentaImprimir.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(imprimirVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonLlegadaMercancia.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(llegadaMercanciaVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });

        botonBaseDeDatos.addActionListener((ActionEvent) -> {
            panelCentral.removeAll();
            panelCentral.add(baseDeDatosVista);
            panelCentral.repaint();
            panelCentral.revalidate();
        });


        //Funciones de botones
        botonCerrarSesion.addActionListener(new BotonCerrarSesion());

        //añadir paneles al frame
        this.add(panelBarraSuperior);
        this.add(panelMenu);
        this.add(panelCentral);
    }

    private void establecerDatosUsuario(String correo) {
        String sql = "SELECT id, nombre, apellido_paterno, apellido_materno, path_foto, administrador FROM Usuario WHERE correo = ?";
        try {
            PreparedStatement ps = ConexionSQL.getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                id = rs.getInt("id");
                nombre = rs.getString("nombre") + " " + rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno");
                pathFoto = rs.getString("path_foto");
                administrador = rs.getBoolean("administrador");
            }
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    private class BotonCerrarSesion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginFrame();
        }
    }


    /*public static void main(String[] args) {
        new PrincipalFrame();
    }*/

}
