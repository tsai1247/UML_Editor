package Workspace.Canvas.Shape;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.*;

public class UseCase extends Shape {
    public UseCase(int x, int y) {
        super(x, y, 100, 80);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // draw use case
        Graphics2D g2d = (Graphics2D) g;
        var Circle = new Ellipse2D.Double(x, y, width, height);
        g2d.setColor(Color.BLACK);
        g2d.draw(Circle);
    }

}
