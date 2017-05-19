package Frames;

import ComponentesBeauty.BeautyImageButton;
import ComponentesBeauty.BeautyList;
import Utilidades.Utilidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
    private BeautyImageButton etiquetaIconoCentral;

    //Paneles para menu
    private JPanel panelInfoUsuario;
    private JPanel panelLista;

    //Componentes para el panel info usuario
    private JLabel botonImagenUsuario;
    private JLabel etiquetaNombreUsuario;
    private JLabel etiquetaCorreoUsuario;

    //Componentes para el panel lista
    private DefaultTableModel dlmListaMenu;
    private BeautyList listaMenu;


    public PrincipalFrame() {
        componentes();
        propiedades();
    }

    private void propiedades() {
        this.setSize(1100,800);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void componentes() {
        //Los 3 paneles principales
        panelBarraSuperior = new JPanel();
        panelMenu = new JPanel();
        panelCentral = new JPanel();

        //Panel barra superior
        botonCerrar = new BeautyImageButton(Utilidades.establecerIcono("Null", 15,15), 5,8,15, 15);
        botonMinimizar = new BeautyImageButton(Utilidades.establecerIcono("Null", 15, 15), 30, 8, 15 ,15);
        etiquetaIconoCentral = new BeautyImageButton(Utilidades.establecerIcono("logo", 10, 20), JButton.CENTER, JButton.CENTER, 1100, 30);

        panelBarraSuperior.setLayout(null);
        panelBarraSuperior.setBackground(new Color(255, 255, 255, 255));
        panelBarraSuperior.setSize(1100, 30);

        panelBarraSuperior.add(botonCerrar);
        panelBarraSuperior.add(botonMinimizar);
        panelBarraSuperior.add(etiquetaIconoCentral);

        //PanelMenu
        panelInfoUsuario = new JPanel();
        panelLista = new JPanel();
        botonImagenUsuario = new JLabel(Utilidades.establecerIcono("FotoPerfil", 70, 70));
        botonImagenUsuario.setBounds(0, 10, 150 ,70);
        botonImagenUsuario.setHorizontalAlignment(JLabel.CENTER);

        etiquetaNombreUsuario = new JLabel("Nombre usuario");
        etiquetaNombreUsuario.setBounds(0, 90, 150, 20);
        etiquetaNombreUsuario.setHorizontalAlignment(JLabel.CENTER);
        etiquetaNombreUsuario.setFont(Utilidades.quickSandFont());
        etiquetaNombreUsuario.setForeground(Color.WHITE);

        etiquetaCorreoUsuario = new JLabel("Correo usuario");
        etiquetaCorreoUsuario.setBounds(0, 120 ,150, 20);
        etiquetaCorreoUsuario.setHorizontalAlignment(JLabel.CENTER);
        etiquetaCorreoUsuario.setFont(Utilidades.quickSandFont());
        etiquetaCorreoUsuario.setForeground(Color.WHITE);

        panelInfoUsuario.setLayout(null);
        panelInfoUsuario.setBounds(0,0,150,160);
        panelInfoUsuario.setBackground(new Color(255, 74,0,108));
        panelInfoUsuario.add(botonImagenUsuario);
        panelInfoUsuario.add(etiquetaNombreUsuario);
        panelInfoUsuario.add(etiquetaCorreoUsuario);



        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 30, 200,580);
        panelMenu.add(panelInfoUsuario);


        this.add(panelBarraSuperior);
        this.add(panelMenu);
    }

    public static void main(String[] args) {
        new PrincipalFrame();
    }

}
