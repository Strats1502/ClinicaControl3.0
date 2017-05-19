package Utilidades;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by esva on 18/05/17.
 */
public class Utilidades {


    public static JLabel fondo() {
        JLabel fondo = new JLabel();
        fondo.setIcon(establecerImagen("fondoGeneral", 800, 500));
        fondo.setBounds(0 ,0 ,800, 500);
        return fondo;
    }


    public static Icon establecerIcono(String archivo,int ancho, int alto) {
        ImageIcon imagen = new ImageIcon("src/Iconos/icn_" + archivo + ".png");
        Icon i = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return i;
    }

    public static Icon establecerImagen(String archivo,int ancho, int alto) {
        ImageIcon imagen = new ImageIcon("src/Imagenes/" + archivo + ".png");
        Icon i = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return i;
    }

    public static Icon establecerImagenPath(String archivo) {
        ImageIcon imagen = new ImageIcon(archivo);
        Icon i = new ImageIcon(imagen.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
        return i;
    }

    public static Font quickSandFont() {
        try {
            Font quickSandFont = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("src/Fuentes/Quicksand.otf"))).
                    deriveFont(Font.PLAIN, 12);
            return quickSandFont;
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
