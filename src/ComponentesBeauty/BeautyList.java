package ComponentesBeauty;

import Utilidades.Utilidades;
import javax.swing.*;
import java.awt.*;

/**
 * Created by esva on 9/05/17.
 */
public class BeautyList extends JScrollPane {

    public BeautyList(DefaultListModel dlm, int x, int y, int ancho, int alto) {
        JList lista = new JList(dlm);
        lista.setLayoutOrientation(JList.VERTICAL);
        lista.setForeground(Color.WHITE);
        lista.setFont(Utilidades.quickSandFont());
        lista.setBackground(new Color(0, 0, 0, 198));
        this.add(lista);
        this.setViewportView(lista);
        this.setPreferredSize(new Dimension(ancho, alto));
        this.setBounds(x, y, ancho, alto);
    }

}
