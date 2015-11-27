package concurrency.synchronization;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public abstract class PaintPanel extends JPanel {
    
    public PaintPanel(int width, int height) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
    }
    
    public abstract void addTriangle(Triangle triangle);
}