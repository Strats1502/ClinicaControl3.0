package ComponentesBeauty;

import Utilidades.Utilidades;
import javax.swing.*;
import java.awt.*;

/**
 * Created by esva on 9/05/17.
 */
public class BeautyList extends JList {

    public BeautyList(DefaultListModel dlm, int x, int y, int ancho, int alto) {
        this.setModel(dlm);
        this.setForeground(Color.WHITE);
        this.setFont(Utilidades.quickSandFont());
        this.setBackground(new Color(0, 0, 0, 198));
        this.setVisible(true);
        this.setBounds(x, y, ancho, alto);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("sf");
        f.setDefaultCloseOperation(3);
        f.setSize(400,400);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
