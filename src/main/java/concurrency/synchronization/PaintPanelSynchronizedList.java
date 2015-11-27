package concurrency.synchronization;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;

public class PaintPanelSynchronizedList extends PaintPanel {
    
    private final List<Triangle> triangles;
    
    public PaintPanelSynchronizedList(int width, int height) {
        super(width, height);
        triangles = Collections.synchronizedList(new ArrayList<>());
    }
    
    @Override
    public void addTriangle(Triangle triangle) {
        triangles.add(triangle);
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* synchronized list doesn't provide synchronized iterator, so we need synchronized block
        synchronized (triangles) {
            for (Triangle triangle : triangles) {
                triangle.draw(g);
            }
        }*/
        for (int i = 0; i < triangles.size(); i++) {
            triangles.get(i).draw(g);
        }
    }
}