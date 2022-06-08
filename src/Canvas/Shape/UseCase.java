package Canvas.Shape;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.*;

public class UseCase extends DecoratorShape {
    public UseCase(Shape shape, int x, int y) {
        super(shape, x, y);
        this.width = 100;
        this.height = 80;
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
