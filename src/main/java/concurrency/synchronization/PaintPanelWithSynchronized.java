package concurrency.synchronization;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class PaintPanelWithSynchronized extends PaintPanel {
    
    private final List<Triangle> triangles;
    
    public PaintPanelWithSynchronized(int width, int height) {
        super(width, height);
        triangles = new ArrayList<>();
    }
    
    @Override
    public void addTriangle(Triangle triangle) {
        synchronized (triangles) {
            triangles.add(triangle);
        }
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (triangles) {
            for (Triangle triangle : triangles) {
                triangle.draw(g);
            }
        }
    }
}