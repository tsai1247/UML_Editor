package Workspace.Canvas.Shape;
import java.awt.*;
import java.util.Vector;

import Workspace.Canvas.CanvasArea;

public class Composite extends Shape {
    public Composite(Vector<Shape> shapes) {
        super(0, 0, 0, 0);
        this.shapes = shapes;
        this.name = "";
        isSelected = true;
        Point startPoint = null, endPoint = null;
        for(Shape shape : shapes) {
            shape.isSelected = false;
            CanvasArea.getInstance().getShapes().remove(shape);
            if(startPoint == null) {
                startPoint = new Point(shape.x, shape.y);
            }
            else
            {
                if(shape.x < startPoint.x) {
                    startPoint.x = shape.x;
                }
                if(shape.y < startPoint.y) {
                    startPoint.y = shape.y;
                }
            }
            if(endPoint == null) {
                endPoint = new Point(shape.x + shape.width, shape.y + shape.height);
            }
            else
            {
                if(shape.x + shape.width > endPoint.x) {
                    endPoint.x = shape.x + shape.width;
                }
                if(shape.y + shape.height > endPoint.y) {
                    endPoint.y = shape.y + shape.height;
                }
            }
        }
        this.x = startPoint.x;
        this.y = startPoint.y;
        this.width = endPoint.x - startPoint.x;
        this.height = endPoint.y - startPoint.y;
        
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // draw shapes
        for(Shape shape : shapes) {
            shape.draw(g);
        }
    }
    
}
