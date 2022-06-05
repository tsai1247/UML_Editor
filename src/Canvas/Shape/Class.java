package Canvas.Shape;
import java.awt.Graphics;

public class Class extends DecoratorShape {
    public Class(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(Graphics g) {
        shape.draw(g);
        //TODO: draw class
    }

}
