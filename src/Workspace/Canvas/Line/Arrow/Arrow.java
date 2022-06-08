package Workspace.Canvas.Line.Arrow;
import java.awt.*;
import java.awt.geom.*;

public class Arrow {
    protected double angle = 30;
    protected double arrowHeadLength = 10;
    protected BasicStroke stroke = new BasicStroke(1.0f);

	public void draw(Graphics g, Point startPoint, Point endPoint) {
        Graphics2D g2d = (Graphics2D) g;
        var Line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(stroke);
        g2d.draw(Line);
        var angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        var x1 = endPoint.x - arrowHeadLength * Math.cos(angle - Math.PI / 6);
        var y1 = endPoint.y - arrowHeadLength * Math.sin(angle - Math.PI / 6);
        var x2 = endPoint.x - arrowHeadLength * Math.cos(angle + Math.PI / 6);
        var y2 = endPoint.y - arrowHeadLength * Math.sin(angle + Math.PI / 6);
        var arrowHead = new Polygon();
        arrowHead.addPoint((int) x1, (int) y1);
        arrowHead.addPoint((int) x2, (int) y2);
        arrowHead.addPoint((int) endPoint.x, (int) endPoint.y);
        g2d.fillPolygon(arrowHead);
    }

    protected void drawALine(Graphics g, Point startPoint, Point endPoint)
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
