package ComponentesBeauty;


import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BeautyComboBox extends JButton{


    public BeautyComboBox(String texto, int x, int y, int ancho, int alto) {
        this.setText(texto);
        this.setIcon(establecerIcono("ComboBox",20,10));
        this.setBackground(Color.BLACK);
        this.setSize(ancho, alto);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.setBounds(x, y, ancho, alto);
        this.setFont(appleFont());
    }

    public Icon establecerIcono(String archivo,int ancho, int alto) {
        ImageIcon imagen = new ImageIcon("src/Iconos/icn_" + archivo + ".png");
        Icon i = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return i;
    }

    /**
     *
     * @return Fuente de Apple
     */

    public Font appleFont() {
        try {
            Font appleFont = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("src/Fuentes/MYRIADAT.TTF"))).
                    deriveFont(Font.PLAIN, 14);
            return appleFont;
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

