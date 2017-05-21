package ComponentesBeauty;

/*
*Edita la apariencia de un boton
*Quita texto y pone imagen
*Tamaño personalizado
 */

import Utilidades.Utilidades;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.*;

/**
 *
 * @author Juan José Estrada Valtierra
 */
public class BeautyImageButton extends JButton{
    
    public BeautyImageButton(Icon icono, int x, int y, int ancho, int alto) {
        this.setText(null);
        this.setContentAreaFilled(false);
        this.setIcon(icono);
        this.setBorder(null);
        this.setBounds(x, y, ancho, alto);
    }

    public BeautyImageButton(Icon icono, int x, int y, int ancho, int alto, String texto) {
        this.setText(texto);
        this.setContentAreaFilled(false);
        this.setIcon(icono);
        this.setBorder(null);
        this.setBounds(x, y, ancho, alto);
        this.setForeground(Color.WHITE);
        this.setFont(Utilidades.quickSandFont());
    }

    //Constructor vacío
    public BeautyImageButton() {}
    
}
