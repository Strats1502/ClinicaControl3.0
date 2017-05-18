package ComponentesBeauty;

/*
*Edita la apariencia de un boton
*Quita texto y pone imagen
*Tamaño personalizado
 */

import javax.swing.Icon;
import javax.swing.JButton;

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

    //Constructor vacío
    public BeautyImageButton() {}
    
}
