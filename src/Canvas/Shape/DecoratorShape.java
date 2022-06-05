package Canvas.Shape;

public abstract class DecoratorShape extends Shape {
    protected Shape shape;
    public DecoratorShape(Shape shape) {
        this.shape = shape;
    }
}
