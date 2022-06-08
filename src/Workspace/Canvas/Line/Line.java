package Workspace.Canvas.Line;
import java.awt.*;
import java.awt.geom.*;

import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;

public class Line {
    protected Shape startShape, endShape;
    protected Port startPort, endPort;
    protected double angle = 30;
    protected double arrowHeadLength = 10;
    
    public Line(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        this.startShape = startShape;
        this.endShape = endShape;
        this.startPort = startPort;
        this.endPort = endPort;

    }

    public void draw(Graphics g)
    {
        var startPoint = startShape.getPosition(startPort);
        var endPoint = endShape.getPosition(endPort);
        drawALine(g, startPoint, endPoint);
    }

    public void drawALine(Graphics g, Point startPoint, Point endPoint)
    {
        Graphics2D g2d = (Graphics2D) g;
        var Line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g2d.setColor(Color.BLACK);
        g2d.draw(Line);
    }

    protected Point getRotatedPoint(Point p, Point center, double angle) {
        double x = p.x - center.x;
        double y = p.y - center.y;
        double x1 = x * Math.cos(angle) - y * Math.sin(angle);
        double y1 = x * Math.sin(angle) + y * Math.cos(angle);
        var pointOfCorrectAngle = new Point((int) (x1 + center.x), (int) (y1 + center.y));
        return getPointWithLength(pointOfCorrectAngle, p, arrowHeadLength);
    }

    // given p and center, return a point which is the length of p from center and between center and p
    protected Point getPointWithLength(Point p, Point center, double length) {
        double x = p.x - center.x;
        double y = p.y - center.y;
        var basicLength = Math.sqrt(x * x + y * y);
        var x1 = x * length / basicLength;
        var y1 = y * length / basicLength;
        var pointOfCorrectLength = new Point((int) (x1 + center.x), (int) (y1 + center.y));
        return pointOfCorrectLength;
    }
}