package ComponentesBeauty;

import Utilidades.Utilidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by esva on 20/05/17.
 */
public class BeautyScrollPane extends JScrollPane {

    public BeautyScrollPane(JList lista, DefaultListModel dlm, int x, int y, int ancho, int alto) {
        lista.setModel(dlm);
        lista.setLayoutOrientation(JList.VERTICAL);
        lista.setForeground(Color.WHITE);
        lista.setFont(Utilidades.quickSandFont());
        lista.setBackground(new Color(0, 0, 0, 198));
        this.add(lista);
        this.setViewportView(lista);
        this.setPreferredSize(new Dimension(ancho, alto));
        this.setBounds(x, y, ancho, alto);
        this.setVisible(false);
    }
}
