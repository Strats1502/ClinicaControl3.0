package ComponentesBeauty;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Juan José Estrada Valtierra
 * @date 13/04/2017
 */
public class BeautyPasswordField extends JPasswordField implements FocusListener {
    private String hint;

    public BeautyPasswordField(String hint, int x, int y, int ancho, int alto) {
        addFocusListener(this);
        setOpaque(false);
        setBorder(null);
        setEchoChar((char) 0);
        this.setHorizontalAlignment(JPasswordField.CENTER);
        this.setFont(appleFont());
        this.setForeground(Color.WHITE);
        this.setBounds(x, y, ancho, alto);
        this.setHint(hint);
    }

    public void setHint(String hint) {
        setText(hint);
        setEchoChar((char) 0);
        this.hint = hint;
    }

    public String getHint() {
        setEchoChar('*');
        return hint;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(getHint())) {
            setText("");
        } else {
            setText(getText());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().length() <= 0) {
            setHint(getHint());

        } else {
            setText(getText());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, getHeight() - 1, getWidth(), 1, 5, 5);

        super.paintComponent(g);

    }

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
