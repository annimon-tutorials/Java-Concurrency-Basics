package concurrency.synchronization;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.DataInputStream;
import java.io.IOException;

public final class Triangle {

    public static Triangle read(DataInputStream dis) throws IOException {
        int color = dis.readInt();
        int x1 = dis.readShort();
        int y1 = dis.readShort();
        int x2 = dis.readShort();
        int y2 = dis.readShort();
        int x3 = dis.readShort();
        int y3 = dis.readShort();
        return new Triangle(color, x1, y1, x2, y2, x3, y3);
    }
    
    private final Color color;
    private final Polygon triangle;
    
    public Triangle(int color, int x1, int y1, int x2, int y2, int x3, int y3) {
        this.color = new Color(color);
        triangle = new Polygon();
        triangle.addPoint(x1, y1);
        triangle.addPoint(x2, y2);
        triangle.addPoint(x3, y3);
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(triangle);
    }
}
