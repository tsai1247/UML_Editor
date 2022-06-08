package Canvas.Shape;
import java.awt.Graphics;
import java.awt.Point;

public class DecoratorShape extends Shape {
    protected Shape shape;
    public DecoratorShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void draw(Graphics g) {
        shape.draw(g);
    }

    @Override
    public Shape getHoveredShape(Point p) {
        if(pointInShape(p)) {
            return this;
        }
        else {
            return shape.getHoveredShape(p);
        }
    }

}
