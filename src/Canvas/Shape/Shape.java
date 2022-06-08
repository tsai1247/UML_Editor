package Canvas.Shape;
import java.awt.*;
import java.util.Vector;

import Canvas.Line.Line;

public abstract class Shape {
    public enum Port {
        EAST, WEST, NORTH, SOUTH, CENTER
    }

    protected Vector<Line> lines = new Vector<Line>();
    protected int x, y; // the top-left corner of the shape
    protected int width, height;

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
            case CENTER:
                return new Point(x + width / 2, y + height / 2);
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
            if(port_ == Port.CENTER)
                continue;
            
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

}
