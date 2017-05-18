package ComponentesBeauty;

import Conexion.ComprobarSQL;
import Vistas.CustomVista;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by esva on 8/05/17.
 */
public class BeautyInputRoot extends JPanel {
    public static BeautyErrorMessage noEsCorreo = new BeautyErrorMessage("Ingresa un correo valido");
    public static BeautyErrorMessage noEsRoot = new BeautyErrorMessage("Usuario no valido");
    public static BeautyErrorMessage noEsContraseña = new BeautyErrorMessage("Contraseña incorrecta");
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public BeautyInputRoot() {
        this.setBounds(160, 180, 450, 140);
        this.setVisible(false);
        this.setLayout(null);

        JLabel fondo = new JLabel();
        fondo.setIcon(CustomVista.establecerImagen("colorGris", 450, 130));
        fondo.setBounds(0,0, 450, 140);

        JLabel mensaje = new JLabel("Contraseña de Superusuario");
        mensaje.setBounds(20, 20, 450,20);
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        mensaje.setFont(CustomVista.appleFont());
        mensaje.setForeground(Color.WHITE);

        JLabel icono = new JLabel();
        icono.setIcon(CustomVista.establecerIcono("logo", 25, 75));
        icono.setBounds(10, 20, 25, 75);

        BeautyTextField txtCorreo = new BeautyTextField("Correo", 140, 40, 200, 20);

        BeautyPasswordField txtContraseña = new BeautyPasswordField("Contraseña", 140, 70, 200, 20);

        BeautyBlackButton btnAceptar = new BeautyBlackButton("Aceptar", 190, 100 , 100, 30);

        btnAceptar.addActionListener((ActionEvent) -> {
            String correo = txtCorreo.getText();
            String contraseña = txtContraseña.getText();
            acceso(correo, contraseña);
        });


        this.add(txtCorreo);
        this.add(txtContraseña);
        this.add(icono);
        this.add(mensaje);
        this.add(btnAceptar);
        this.add(fondo);
    }

    private void acceso(String correo, String contraseña) {
        if(esCorreo(correo)) {
            if (correo.equals("juanjoseesva@gmail.com")) {
                if (contraseña.equals("123asdZXC")) {
                    this.setVisible(false);
                } else {
                    noEsContraseña.setVisible(true);
                }
            } else {
                noEsRoot.setVisible(true);
            }
        } else {
            noEsCorreo.setVisible(true);
        }
    }

    private static boolean esCorreo(String correo) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLayout(null);
        f.add(noEsCorreo);
        f.add(noEsRoot);
        f.add(noEsContraseña);
        f.add(new BeautyInputRoot());
        f.setSize(800,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
    }

}
