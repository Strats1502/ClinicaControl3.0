package ComponentesBeauty;

import Utilidades.Utilidades;
import Vistas.NuevoPacienteVista;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by esva on 21/05/17.
 */
public class BeautyDateChooser extends JDateChooser implements FocusListener{

    public BeautyDateChooser(int x, int y, int ancho, int alto) {
        addFocusListener(this);
        this.setBounds(x, y, ancho, alto);
        this.setFont(quickSandFont());
        this.setForeground(new Color(51, 53, 68));
        setOpaque(false);
        setBorder(null);
        this.setBorder(null);
    }


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(51, 53, 68));
        g.fillRoundRect(0, this.getHeight() - 1, getWidth(), 1, 5, 5);
        super.paintComponent(g);
    }

    public Font quickSandFont() {
        try {
            Font quickSandFont = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("src/Fuentes/Quicksand.otf"))).
                    deriveFont(Font.PLAIN, 16);
            return quickSandFont;
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("eee");
        f.setLayout(null);
        f.add(new BeautyDateChooser(0,0, 200, 20));
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setSize(750, 625);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
