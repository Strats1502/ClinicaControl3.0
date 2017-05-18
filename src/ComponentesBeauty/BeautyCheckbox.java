package ComponentesBeauty;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by esva on 25/04/17.
 */
public class BeautyCheckbox extends JCheckBox {


    public BeautyCheckbox(String texto, int x, int y, int ancho) {
        this.setBounds(x,y,ancho,20);
        this.setBackground(new Color(0,0,0,0));
        this.setText(texto);
        this.setFont(appleFont());
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setPressedIcon(establecerIcono("CheckBox",ancho,20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0,0,0,0));
        g.fillRoundRect(0,0,this.getWidth(),this.getHeight(),10,10);
        super.paintComponent(g);
    }

    public Icon establecerIcono(String archivo,int ancho, int alto) {
        ImageIcon imagen = new ImageIcon("src/Iconos/icn_" + archivo + ".png");
        Icon i = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return i;
    }

    private Font appleFont() {
        try {
            Font appleFont = Font.createFont(Font.TRUETYPE_FONT,
                             new FileInputStream(new File("src/Fuentes/MYRIADAT.TTF"))).deriveFont(Font.PLAIN,14);
            return appleFont;
        } catch (Exception e) {
            System.out.println("No se encontro la fuente de la tipografía");
        }
        return null;
    }
}
