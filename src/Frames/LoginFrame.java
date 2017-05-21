package Frames;

import ComponentesBeauty.*;
import Conexion.ComprobarSQL;
import Utilidades.Utilidades;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by esva on 20/05/17.
 */
public class LoginFrame extends JFrame {
    //Botones barra superior
    private BeautyImageButton botonCerrar;
    private BeautyImageButton botonMinimizar;
    private JPanel panelBarraSuperior;

    private JLabel etiquetaSuperior;
    private JPanel panelInferior;

    private BeautyTextField campoCorreo;
    private BeautyPasswordField campoPassword;
    private BeautyBlackButton botonAceptar;


    public  BeautyErrorMessage noEsCorreo = new BeautyErrorMessage("No es correo");
    public  BeautyErrorMessage noExiste= new BeautyErrorMessage("Usuario no existe");
    public  BeautyErrorMessage noEsContraseña = new BeautyErrorMessage("Usuario existe pero contraseña invalida");
    public  BeautyErrorMessage noActivo = new BeautyErrorMessage("Usuario existe pero no esta activo");

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public LoginFrame() {
        this.add(noEsCorreo);
        this.add(noEsContraseña);
        this.add(noExiste);
        this.add(noActivo);
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

    private void componentes() {
        //Panel barra superior
        panelBarraSuperior = new JPanel();
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


        etiquetaSuperior = new JLabel();
        etiquetaSuperior.setOpaque(true);
        etiquetaSuperior.setBounds(0,30,900, 330);
        etiquetaSuperior.setBackground(new Color(73, 201, 180, 255));
        etiquetaSuperior.setIcon(Utilidades.establecerIcono("logo", 65, 200));
        etiquetaSuperior.setHorizontalAlignment(JLabel.CENTER);

        panelInferior = new JPanel();

        campoCorreo = new BeautyTextField("Correo", 240, 100, 400, 20);
        campoPassword = new BeautyPasswordField("Contraseña", 240, 170, 400, 20);
        botonAceptar = new BeautyBlackButton("Iniciar sesión", 340, 230, 200, 30);

        botonAceptar.addActionListener((ActionEvent) -> {
            String correo = campoCorreo.getText();
            String contraseña = campoPassword.getText();
            acceso(correo, contraseña);
        });

        panelInferior.setLayout(null);
        panelInferior.setBounds(0,330,900, 325);
        panelInferior.add(campoCorreo);
        panelInferior.add(campoPassword);
        panelInferior.add(botonAceptar);

        this.add(panelBarraSuperior);
        this.add(etiquetaSuperior);
        this.add(panelInferior);
    }

    private void acceso(String correo, String contraseña) {
        if(esCorreo(correo)) {
            if(ComprobarSQL.existeCorreo(correo)) {
                if(ComprobarSQL.existeContraseña(correo, contraseña)) {
                    if (ComprobarSQL.activo(correo,contraseña)) {
                        this.dispose();
                        new PrincipalFrame(correo);
                    } else {
                        noActivo.setVisible(true);
                    }
                } else {
                    noEsContraseña.setVisible(true);
                }
            } else {
                noExiste.setVisible(true);
            }
        } else {
            noEsCorreo.setVisible(true);
        }
    }

    private boolean esCorreo(String correo) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
