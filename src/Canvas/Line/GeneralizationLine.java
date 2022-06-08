package Canvas.Line;
import java.awt.*;
import java.awt.geom.*;

import Canvas.Shape.Shape;
import Canvas.Shape.Shape.Port;

public class GeneralizationLine extends Line {
    public GeneralizationLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        super(startShape, startPort, endShape, endPort);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        super.draw(g);
        
        // draw the arrow
        Graphics2D g2d = (Graphics2D) g;
        var startPoint = startShape.getPosition(startPort);
        var endPoint = endShape.getPosition(endPort);
        var arrowHeadLeftPoint = getRotatedPoint(endPoint, startPoint, angle);
        var arrowHeadRightPoint = getRotatedPoint(endPoint, startPoint, -angle);
        drawALine(g2d, startPoint, endPoint);
        drawALine(g2d, endPoint, arrowHeadLeftPoint);
        drawALine(g2d, endPoint, arrowHeadRightPoint);
        drawALine(g2d, arrowHeadLeftPoint, arrowHeadRightPoint);
    }
}