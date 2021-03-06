package ComponentesBeauty;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JTextField;

public class BeautyTextField extends JTextField implements FocusListener {
    private String hint;

    public BeautyTextField(String hint, int x, int y, int ancho, int alto) {
        addFocusListener(this);
        this.setBounds(x, y, ancho, alto);
        this.setFont(quickSandFont());
        this.setForeground(new Color(51, 53, 68));
        this.setHint(hint);
        setOpaque(false);
        setBorder(null);
        setHorizontalAlignment(JTextField.CENTER);
    }

    public void setHint(String hint) {
        setText(hint);
        this.hint = hint;
    }

    public String getHint() {
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

}
