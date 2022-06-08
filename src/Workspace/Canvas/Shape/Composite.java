package Workspace.Canvas.Shape;
import java.awt.*;
import java.util.Vector;

public class Composite extends DecoratorShape {
    public Composite(Shape shape) {
        super(shape, 0, 0);
        isSelected = true;
        Point startPoint = null, endPoint = null;
        Shape curShape = this;

        while(curShape.shape != null) {
            if(curShape.shape.isSelected()) {
                curShape.shape.isSelected = false;
                shapes.add(curShape.shape);
                if(startPoint == null) {
                    startPoint = new Point(curShape.shape.x, curShape.shape.y);
                }
                else
                {
                    if(startPoint.x > curShape.shape.x)
                        startPoint.x = curShape.shape.x;
                    if(startPoint.y > curShape.shape.y)
                        startPoint.y = curShape.shape.y;
                }
                if(endPoint == null) {
                    endPoint = new Point(curShape.shape.x + curShape.shape.width, curShape.shape.y + curShape.shape.height);
                }
                else
                {
                    if(endPoint.x < shape.x + curShape.shape.width)
                        endPoint.x = shape.x + curShape.shape.width;
                    if(endPoint.y < shape.y + curShape.shape.height)
                        endPoint.y = shape.y + curShape.shape.height;
                }
                curShape.shape = curShape.shape.shape;
            }
            else
            {
                curShape = curShape.shape;
            }

        }
        if(startPoint != null && endPoint != null) {
            this.x = startPoint.x;
            this.y = startPoint.y;
            this.width = endPoint.x - startPoint.x;
            this.height = endPoint.y - startPoint.y;
        }
        else
        {
            this.x = 0;
            this.y = 0;
            this.width = 0;
            this.height = 0;
        }
    }

    protected Vector<Shape> shapes = new Vector<Shape>();

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // draw shapes
        for(Shape shape : shapes) {
            shape.draw(g);
        }
    }
    
}
