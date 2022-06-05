package Canvas.Shape;
import java.awt.Graphics;

public class UseCase extends DecoratorShape {
    public UseCase(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(Graphics g) {
        shape.draw(g);
        //TODO: draw use case
    }

}
