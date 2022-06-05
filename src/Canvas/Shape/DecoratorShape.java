package Canvas.Shape;

public abstract class DecoratorShape extends Shape {
    protected Shape shape;
    protected int x, y;
    public DecoratorShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }
}
