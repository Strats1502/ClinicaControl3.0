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
        this.setForeground(new Color(51, 53, 68));
        this.setOpaque(false);
        this.setBounds(x, y, ancho, alto);
        this.setFont(quickSandFont());
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

    public Font quickSandFont() {
        try {
            Font quickSandFont = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("src/Fuentes/Quicksand.otf"))).
                    deriveFont(Font.PLAIN, 14);
            return quickSandFont;
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

