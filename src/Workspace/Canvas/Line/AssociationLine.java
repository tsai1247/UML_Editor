package Workspace.Canvas.Line;
import java.awt.*;

import Workspace.Canvas.Shape.Shape;
import Workspace.Canvas.Shape.Shape.Port;

public class AssociationLine extends Line {
    public AssociationLine(Shape startShape, Port startPort, Shape endShape, Port endPort) {
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
        drawALine(g2d, startPoint, endPoint);
        drawALine(g2d, endPoint, arrowHeadLeftPoint);
        drawALine(g2d, endPoint, arrowHeadRightPoint);
    }
}