package Vistas;

import javax.swing.*;
import Utilidades.Utilidades;

/**
 * Created by esva on 20/05/17.
 */
public class HomeVista extends JPanel{

    private static JLabel lblFondo;
    private static int contador = 1;

    public HomeVista() {
        this.setLayout(null);
        this.setBounds(0,0,750,625);
        this.setOpaque(true);

        lblFondo = new JLabel();
        lblFondo.setBounds(0, 0, 750, 625);
        lblFondo.setIcon(Utilidades.establecerImagen("1", 750, 625));

        cambiarImagen();
        this.add(lblFondo);
    }

    private static void cambiarImagen() {
        Timer timer = new Timer(2000, (ActionEvent -> {
            String cad = String.valueOf(contador);
            lblFondo.setIcon(Utilidades.establecerImagen(cad, 750, 625));
            contador ++;
            if (contador == 4) contador = 1;
        }));
        timer.start();
    }
}
