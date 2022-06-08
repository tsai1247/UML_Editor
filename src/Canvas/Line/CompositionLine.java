package Canvas.Line;
import java.awt.*;

import Canvas.Shape.Shape;
import Canvas.Shape.Shape.Port;

public class CompositionLine extends Line {
    public CompositionLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
        super(startShape, startPort, endShape, endPort);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        
        // draw the arrow
        Graphics2D g2d = (Graphics2D) g;
        var startPoint = startShape.getPosition(startPort);
        var endPoint = endShape.getPosition(endPort);
        var arrowHeadLeftPoint = getRotatedPoint(endPoint, startPoint, angle);
        var arrowHeadRightPoint = getRotatedPoint(endPoint, startPoint, -angle);
        var arrowHeadBackPoint = getPointWithLength(startPoint, endPoint, arrowHeadLength  * Math.sqrt(2));

        drawALine(g2d, startPoint, endPoint);
        drawALine(g2d, endPoint, arrowHeadLeftPoint);
        drawALine(g2d, endPoint, arrowHeadRightPoint);
        drawALine(g2d, arrowHeadBackPoint, arrowHeadLeftPoint);
        drawALine(g2d, arrowHeadBackPoint, arrowHeadRightPoint);
        
    }
}