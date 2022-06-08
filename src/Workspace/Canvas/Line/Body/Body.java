package Workspace.Canvas.Line.Body;
import java.awt.*;
import java.awt.geom.*;

public class Body {
    protected BasicStroke stroke = new BasicStroke(1.0f);
    protected BasicStroke highlightedstroke = new BasicStroke(3.0f);
    
    protected boolean isHighlighted = false;
    public void setHighlighted(boolean b) {
        isHighlighted = b;
    }
    
    public void draw(Graphics g, Point startPoint, Point endPoint) {
        Graphics2D g2d = (Graphics2D) g;
        var Line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(isHighlighted ? highlightedstroke : stroke);
        g2d.draw(Line);
    }

}
