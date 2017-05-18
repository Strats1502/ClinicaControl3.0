package ComponentesBeauty;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by esva on 25/04/17.
 */
public class BeautyBlackButton extends JButton {

    public BeautyBlackButton(String texto, int x, int y, int ancho, int alto) {
        this.setText(texto);
        this.setSize(ancho, alto);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.setBounds(x, y, ancho, alto);
        this.setFont(appleFont());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
        super.paintComponent(g);
    }

    /**
     *
     * @return Fuente de Apple
     */

    public Font appleFont() {
        try {
            Font appleFont = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("src/Fuentes/MYRIADAT.TTF"))).
                    deriveFont(Font.PLAIN, 16);
            return appleFont;
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
