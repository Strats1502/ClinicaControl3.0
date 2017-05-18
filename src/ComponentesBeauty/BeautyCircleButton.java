package ComponentesBeauty;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * Created by esva on 21/04/17.
 */
public class BeautyCircleButton extends JButton{
    AbstractBorder bordeCircular = new CircleBorder();

    public BeautyCircleButton(Icon icono,int x, int y,int ancho, int alto) {
        this.setBounds(x, y, ancho, alto);
        this.setBorder(bordeCircular);
        this.setIcon(icono);
        this.setContentAreaFilled(false);
        this.setBackground(new Color(0,0,0,0));
    }

    private class CircleBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            Ellipse2D circle2D = new Ellipse2D.Double();

            circle2D.setFrameFromCenter(
                    new Point(x + width / 2, y + height / 2),
                    new Point(width, height));

            Polygon pointer = new Polygon();
            Area area = new Area(circle2D);
            area.add(new Area(pointer));

            //pinta el fondo con el color del componente padre
            Component parent = c.getParent();
            if (parent != null) {
                Color bg = new Color(0,0,0,0);
                Rectangle rect = new Rectangle(0, 0, width, height);
                Area borderRegion = new Area(rect);
                borderRegion.subtract(area);
                g2.setClip(borderRegion);
                g2.setColor(null);
                g2.fillRect(0, 0, width, height);
                g2.setClip(null);
            }

            g2.draw(area);
        }

    }



}
