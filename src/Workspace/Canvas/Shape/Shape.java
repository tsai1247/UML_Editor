package Workspace.Canvas.Shape;
import java.awt.*;
import java.util.Vector;

import Workspace.Canvas.Line.Line;

public abstract class Shape {
    
    protected Shape shape;
    public enum Port {
        EAST, WEST, NORTH, SOUTH
    }

    protected Vector<Line> lines = new Vector<Line>();
    protected int x, y; // the top-left corner of the shape
    protected int width, height;
    protected String name;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    protected boolean isSelected = false;
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public abstract void setAllShapesBelowSelected(boolean isSelected);
    
    public Point getPosition(Port port) {
        switch (port) {
            case EAST:
                return new Point(x + width, y + height / 2);
            case WEST:
                return new Point(x, y + height / 2);
            case NORTH:
                return new Point(x + width / 2, y);
            case SOUTH:
                return new Point(x + width / 2, y + height);
            default:
                return null;
        }
    }
    public abstract void draw(Graphics g);
    public Shape getHoveredShape(Point p)
    {
        return null;
    }
    public Port getHoveredPort(Point p)
    {
        Port port = Port.EAST;
        for(Port port_ : Port.values())
        {
            // not to check the center value
            if(p.distance(getPosition(port_)) < p.distance(getPosition(port)))
            {
                port = port_;
            }
        }
        return port;
    }

    protected boolean pointInShape(Point p) {
        return p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height;
    }
    public void setSelected(Point startPoint, Point endPoint) {
        // make startPoint be the top-left corner of the shape, endPoint be the bottom-right corner of the shape
        if(startPoint.x > endPoint.x) {
            int temp = startPoint.x;
            startPoint.x = endPoint.x;
            endPoint.x = temp;
        }
        if(startPoint.y > endPoint.y) {
            int temp = startPoint.y;
            startPoint.y = endPoint.y;
            endPoint.y = temp;
        }

        // shape is selected if it is in the rectangle of startPoint and endPoint
        isSelected = startPoint.x <= x && startPoint.y <= y && endPoint.x >= x + width && endPoint.y >= y + height;
        
    }
    public String getName() {
        return name;
    }

}
