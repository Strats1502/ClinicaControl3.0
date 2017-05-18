package ComponentesBeauty;

import javax.swing.*;
import java.awt.*;

/**
 * Created by esva on 6/05/17.
 */
public class BeautyComboBoxOptions extends JPanel{

    public BeautyComboBoxOptions(int renglones, int x, int y, int ancho, int alto) {
        this.setLayout(new GridLayout(renglones, 1));
        this.setBackground(new Color(0,0,0,198));
        this.setBounds(x, y, ancho, alto);
    }

}
