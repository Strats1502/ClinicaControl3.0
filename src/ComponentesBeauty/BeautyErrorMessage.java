package ComponentesBeauty;

import Utilidades.Utilidades;

import javax.swing.*;
import java.awt.*;

/**
 * Created by esva on 6/05/17.
 */
public class BeautyErrorMessage extends JPanel {

    public BeautyErrorMessage(String mensaje) {
        this.setVisible(false);
        this.setLayout(null);
        this.setBackground(new Color(0,0,0, 198));
        this.setBounds(220, 250, 450, 130);

        JLabel icono = new JLabel();
        icono.setIcon(Utilidades.establecerIcono("logo", 25, 75));
        icono.setBounds(10, 20, 25, 75);

        JLabel msg = new JLabel("Error: " + mensaje);
        msg.setForeground(Color.WHITE);
        msg.setFont(Utilidades.quickSandFont());
        msg.setHorizontalAlignment(JLabel.CENTER);
        msg.setBounds(50, 40, 350, 30);

        BeautyBlackButton btnAceptar = new BeautyBlackButton("Aceptar", 170, 90, 100, 30);

        btnAceptar.addActionListener((ActionEvent) -> {
            this.setVisible(false);
        });

        this.add(icono);
        this.add(msg);
        this.add(btnAceptar);
    }

}
