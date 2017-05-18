package ComponentesBeauty;

/*
*Frame personalizado
*Quita borde
*Contiene metodo de establecer un icono a cualquier widget
 */
import com.sun.awt.AWTUtilities;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Juan José Estrada Valtierra
 */
public class BeautyFrame extends JFrame{
    
    public BeautyFrame(String titulo, int largo, int ancho) {
        this.setTitle(titulo);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(largo,ancho);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("src/Imagenes/Corazon.png").getImage());
        //le establece una forma a la ventana
        Shape forma = new RoundRectangle2D.Double(0,0,this.getBounds().width,this.getBounds().height,27,27);
        AWTUtilities.setWindowShape(this, forma);
    }
    
    public Icon establecerIcono(String archivo,int ancho, int alto) {
        ImageIcon imagen = new ImageIcon("src/Imagenes/" + archivo + ".png");
        Icon i = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return i;
    }
}
