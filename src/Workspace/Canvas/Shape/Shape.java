package Workspace.Canvas.Shape;
import java.awt.*;
import java.util.Vector;

public class Shape {
    public enum Port {
        EAST, WEST, NORTH, SOUTH
    }

    public Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = getClass().getSimpleName();
    }

    
    protected Vector<Shape> shapes = new Vector<Shape>();
    public Vector<Shape> getShapes() {
        return shapes;
    }
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
    public void draw(Graphics g)
    {
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

        // draw name
        g.setColor(Color.BLACK);
        g.drawString(name, x + width / 2 - name.length() * 3, y + height / 2);
    }

    public boolean isHovered(Point p)
    {
        return pointInShape(p);
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
    public Port getClickedPort(Point p)
    {
        for(Port port : Port.values())
        {
            if(p.distance(getPosition(port)) < 10)
            {
                return port;
            }
        }
        return null;
    }

    protected boolean pointInShape(Point p) {
        return p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isInRectangle(Point startPoint, Point endPoint) {
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
        
        return startPoint.x <= x && startPoint.y <= y && endPoint.x >= x + width && endPoint.y >= y + height;
    }
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        for (Shape shape : shapes) {
            shape.move(dx, dy);
        }
    }

}
