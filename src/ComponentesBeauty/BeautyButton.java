package ComponentesBeauty;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Juan José Estrada Valtierra on 21/04/17.
 */
public class BeautyButton extends JButton {

    public BeautyButton(String texto, int x, int y, int ancho, int alto) {
        this.setText(texto);
        this.setBackground(new Color(0,0,0,0));
        this.setSize(ancho, alto);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.setBounds(x, y, ancho, alto);
        this.setFont(quickSandFont());

    }

    /**
     *
     * @return Fuente de Apple
     */

    public Font quickSandFont() {
        try {
            Font quickSandFont = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("src/Fuentes/MYRIADAT.TTF"))).
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
