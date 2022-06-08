package Workspace.Canvas.Shape;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.*;

public class DecoratorShape extends Shape {
    public DecoratorShape(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.name = getClass().getSimpleName();
    }
    
    @Override
    public void draw(Graphics g) {
        shape.draw(g);

        if(isSelected) {
            // draw selection at four ports
            var g2d = (Graphics2D) g;
            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(3));
            for(Port port : Port.values()) {
                g2d.drawRect(getPosition(port).x - 5, getPosition(port).y - 5, 10, 10);
            }
            
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
        }

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

    @Override
    public void setAllShapesBelowSelected(boolean isSelected) {
        this.isSelected = isSelected;
        shape.setAllShapesBelowSelected(isSelected);        
    }

    @Override
    public void setSelected(Point startPoint, Point endPoint) {
        super.setSelected(startPoint, endPoint);
        shape.setSelected(startPoint, endPoint);
    }

}
