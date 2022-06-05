package Canvas.Shape;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.*;

public class Class extends DecoratorShape {
    private int width=100, height=80;
    private int layerNum = 3;

    public Class(Shape shape, int x, int y) {
        super(shape, x, y);
    }

    @Override
    public void draw(Graphics g) {
        shape.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0; i<layerNum; i++) {
            var Rectangle = new Rectangle2D.Double(x, y + height/layerNum*i, width, height/layerNum);
            g2d.setColor(Color.BLACK);
            g2d.draw(Rectangle);
        }
    }

}
