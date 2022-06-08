package Workspace.Canvas.Line.Body;
import java.awt.*;
import java.awt.geom.*;

public class Body {
    protected BasicStroke stroke = new BasicStroke(1.0f);
    
    
    public void draw(Graphics g, Point startPoint, Point endPoint) {
        Graphics2D g2d = (Graphics2D) g;
        var Line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(stroke);

        g2d.draw(Line);
    }
}
