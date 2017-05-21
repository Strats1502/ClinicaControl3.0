package ComponentesBeauty;

import Utilidades.Utilidades;
import javax.swing.*;
import java.awt.*;

/**
 * Created by esva on 9/05/17.
 */
public class BeautyList extends JList {

    public BeautyList() {
        this.setLayoutOrientation(JList.VERTICAL);
        this.setForeground(Color.WHITE);
        this.setFont(Utilidades.quickSandFont());
        this.setBackground(new Color(0, 0, 0, 198));
    }

}
