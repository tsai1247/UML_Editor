package Canvas.Shape;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.*;

public class UseCase extends DecoratorShape {
    private int width=100, height=80;
    public UseCase(Shape shape, int x, int y) {
        super(shape, x, y);
    }

    @Override
    public void draw(Graphics g) {
        shape.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        var Circle = new Ellipse2D.Double(x, y, width, height);
        g2d.setColor(Color.BLACK);
        g2d.draw(Circle);
    }

}
