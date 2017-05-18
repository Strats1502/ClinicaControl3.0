package Frames;

import ComponentesBeauty.BeautyImageButton;
import ComponentesBeauty.BeautyList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private BeautyImageButton botonImagenUsuario;
    private JLabel etiquetaNombreUsuario;
    private JLabel etiquetaCorreoUsuario;

    //Componentes para el panel lista
    private DefaultTableModel dlmListaMenu;
    private BeautyList listaMenu;

    private void propiedades() {
        this.setSize(900,600);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void componentes() {

    }

}
